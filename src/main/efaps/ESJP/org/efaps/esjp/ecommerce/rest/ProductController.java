/*
 * Copyright © 2003 - 2024 The eFaps Team (-)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.efaps.esjp.ecommerce.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;
import org.efaps.db.Instance;
import org.efaps.eql.EQL;
import org.efaps.eql.builder.Selectables;
import org.efaps.esjp.ci.CIECom;
import org.efaps.esjp.ci.CIProducts;
import org.efaps.esjp.db.InstanceUtils;
import org.efaps.esjp.ecommerce.rest.dto.CategoryDto;
import org.efaps.esjp.ecommerce.rest.dto.PagedDto;
import org.efaps.esjp.ecommerce.rest.dto.PaginationDto;
import org.efaps.esjp.ecommerce.rest.dto.ProductDto;
import org.efaps.esjp.ecommerce.rest.dto.ProductInclude;
import org.efaps.util.EFapsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@EFapsUUID("61ef6b8c-ab1a-4aa1-ad47-33811396212c")
@EFapsApplication("eFapsApp-ECommerce")
@Path("/ecommerce/products")
public class ProductController
    extends AbstractController
{
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@QueryParam("limit") final int limit,
                                @QueryParam("page") final int page,
                                @QueryParam("include") final Set<ProductInclude> include,
                                @QueryParam("categories") final Set<String> categoryOids)
        throws EFapsException
    {
        checkAccess();
        final var offset = (page - 1) * limit;
        final var inclCategories = include != null && include.contains(ProductInclude.CATEGORIES);

        final var whereBldr = EQL.builder().where()
                        .attribute(CIProducts.ProductAbstract.Active).eq("true");

        if (CollectionUtils.isEmpty(categoryOids)) {
            whereBldr.and()
                            .attribute(CIProducts.ProductAbstract.ID).in(
                                            EQL.builder()
                                                            .nestedQuery(CIECom.Category2Product)
                                                            .selectable(Selectables.attribute(
                                                                            CIECom.Category2Product.ToLink)));

        } else {
            final var catInts = categoryOids.stream()
                            .map(Instance::get)
                            .filter(inst -> InstanceUtils.isKindOf(inst, CIECom.Category))
                            .toList();

            whereBldr.and()
                            .attribute(CIProducts.ProductAbstract.ID).in(
                                            EQL.builder()
                                                            .nestedQuery(CIECom.Category2Product)
                                                            .where()
                                                            .attribute(CIECom.Category2Product.FromLink).in(catInts)
                                                            .up()
                                                            .selectable(Selectables.attribute(
                                                                            CIECom.Category2Product.ToLink)));
        }

        final var select = EQL.builder().print()
                        .query(CIProducts.ProductAbstract)
                        .where(whereBldr)
                        .select()
                        .attribute(CIProducts.ProductAbstract.ID, CIProducts.ProductAbstract.Name,
                                        CIProducts.ProductAbstract.Description);

        if (inclCategories) {
            select.linkfrom(CIECom.Category2Product.ToLink)
                            .linkto(CIECom.Category2Product.FromLink).attribute(CIECom.Category.OID).as("catOid")
                            .linkfrom(CIECom.Category2Product.ToLink)
                            .linkto(CIECom.Category2Product.FromLink).attribute(CIECom.Category.Name).as("catName")
                            .linkfrom(CIECom.Category2Product.ToLink)
                            .linkto(CIECom.Category2Product.FromLink).attribute(CIECom.Category.Label).as("catLabel");
        }

        final var eval = select.limit(limit)
                        .offset(offset)
                        .orderBy(CIProducts.ProductAbstract.ID)
                        .evaluate();

        final var countEval = EQL.builder().count(CIProducts.ProductAbstract)
                        .where(whereBldr)
                        .evaluate();

        final List<ProductDto> products = new ArrayList<>();
        while (eval.next()) {
            Set<CategoryDto> categories = null;
            if (inclCategories) {
                categories = new HashSet<>();
                final var catNames = eval.<List<String>>get("catName").iterator();
                final var catLabels= eval.<List<String>>get("catLabel").iterator();
                for (final String element : eval.<List<String>>get("catOid")) {
                    categories.add(CategoryDto.builder()
                                    .withOid(element)
                                    .withName(catNames.next())
                                    .withLabel(catLabels.next())
                                    .withChildCategories(null)
                                    .build());
                }
            }

            products.add(ProductDto.builder()
                            .withOid(eval.inst().getOid())
                            .withSku(eval.get(CIProducts.ProductAbstract.Name))
                            .withName(eval.get(CIProducts.ProductAbstract.Description))
                            .withCategories(categories)
                            .build());
        }

        final var dto = PagedDto.<ProductDto>builder()
                        .withContent(products)
                        .withPagination(PaginationDto.builder()
                                        .withLimit(limit)
                                        .withPage(page)
                                        .withTotal(Long.valueOf(countEval.count()).intValue())
                                        .build())
                        .build();

        final Response ret = Response.ok()
                        .type(MediaType.APPLICATION_JSON)
                        .entity(dto)
                        .build();
        return ret;
    }
}
