package org.efaps.esjp.ecommerce.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;
import org.efaps.eql.EQL;
import org.efaps.esjp.ci.CIProducts;
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
    public Response getProducts()
        throws EFapsException
    {
        checkAccess();
        final var eval = EQL.builder().print().query(CIProducts.ProductAbstract)
            .select()
            .attribute(CIProducts.ProductAbstract.Name, CIProducts.ProductAbstract.Description)
            .evaluate();

        final List<ProductDto> products = new ArrayList<>();
        while (eval.next()) {
            products.add(ProductDto.builder()
                            .withSku(eval.get(CIProducts.ProductAbstract.Name))
                            .withName(eval.get(CIProducts.ProductAbstract.Description))
                            .build());
        }

        final Response ret = Response.ok()
                        .entity(products)
                        .build();
        return ret;
    }
}
