package org.efaps.esjp.ecommerce.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;
import org.efaps.eql.EQL;
import org.efaps.esjp.ci.CIProducts;
import org.efaps.esjp.ecommerce.rest.dto.PagedDto;
import org.efaps.esjp.ecommerce.rest.dto.PaginationDto;
import org.efaps.esjp.ecommerce.rest.dto.ProductDto;
import org.efaps.util.EFapsException;

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
                        .attribute(CIProducts.ProductAbstract.Name, CIProducts.ProductAbstract.Description)
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
                        .entity(dto)
                        .build();
        return ret;
    }
}
