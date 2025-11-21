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
import java.util.List;

import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;
import org.efaps.eql.EQL;
import org.efaps.esjp.ci.CIProducts;
import org.efaps.esjp.ecommerce.rest.dto.PagedDto;
import org.efaps.esjp.ecommerce.rest.dto.PaginationDto;
import org.efaps.esjp.ecommerce.rest.dto.ProductDto;
import org.efaps.util.EFapsException;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@QueryParam("limit") final int limit,
                                @QueryParam("page") final int page)
        throws EFapsException
    {
        checkAccess();
        final var offset = (page - 1) * limit;

        final var eval = EQL.builder().print()
                        .query(CIProducts.ProductAbstract)
                        .select()
                        .attribute(CIProducts.ProductAbstract.ID, CIProducts.ProductAbstract.Name,
                                        CIProducts.ProductAbstract.Description)
                        .limit(limit)
                        .offset(offset)
                        .orderBy(CIProducts.ProductAbstract.ID)
                        .evaluate();

        final var countEval = EQL.builder().count(CIProducts.ProductAbstract).stmt()
                        .evaluate();

        final List<ProductDto> products = new ArrayList<>();
        while (eval.next()) {
            products.add(ProductDto.builder()
                            .withSku(eval.get(CIProducts.ProductAbstract.Name))
                            .withName(eval.get(CIProducts.ProductAbstract.Description))
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
