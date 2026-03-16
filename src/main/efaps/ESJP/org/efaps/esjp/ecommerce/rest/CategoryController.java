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
import java.util.HashMap;

import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;
import org.efaps.eql.EQL;
import org.efaps.esjp.ci.CIECom;
import org.efaps.esjp.ecommerce.rest.dto.CategoryDto;
import org.efaps.esjp.ecommerce.rest.dto.CategoryDto.Builder;
import org.efaps.util.EFapsException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@EFapsUUID("358d6f38-7d6f-48fc-9ceb-e25427b96e4a")
@EFapsApplication("eFapsApp-ECommerce")
@Path("/ecommerce/categories")
public class CategoryController
    extends AbstractController
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories()
        throws EFapsException
    {
        checkAccess();

        final var eval = EQL.builder().print()
                        .query(CIECom.Category)
                        .select()
                        .attribute(CIECom.Category.ID, CIECom.Category.Name, CIECom.Category.Label,
                                        CIECom.Category.ParentLink)
                        .orderBy(CIECom.Category.ID)
                        .evaluate();
        final var allCategoriesBldr = new HashMap<Long, CategoryDto.Builder>();
        while (eval.next()) {
            allCategoriesBldr.put(eval.inst().getId(), CategoryDto.builder()
                            .withOid(eval.inst().getOid())
                            .withName(eval.get(CIECom.Category.Name))
                            .withLabel(eval.get(CIECom.Category.Label))
                            .setParentId(eval.get(CIECom.Category.ParentLink)));
        }

        final var rootBlrds = new ArrayList<CategoryDto.Builder>();
        allCategoriesBldr.forEach((id,
                                   bldr) -> {
            if (bldr.getParentId() != null && allCategoriesBldr.containsKey(bldr.getParentId())) {
                allCategoriesBldr.get(bldr.getParentId()).addChildBdlr(bldr);
            } else {
                rootBlrds.add(bldr);
            }
        });

        final var dtos = rootBlrds.stream().map(Builder::build).toList();

        final Response ret = Response.ok()
                        .type(MediaType.APPLICATION_JSON)
                        .entity(dtos)
                        .build();
        return ret;
    }
}
