package org.efaps.esjp.ecommerce.rest;

import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;
import org.efaps.esjp.ecommerce.util.ECommerce;
import org.efaps.util.EFapsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@EFapsUUID("60616587-6a4b-4694-af9b-a2f609353bd7")
@EFapsApplication("eFapsApp-ECommerce")
public abstract class AbstractController
{

    private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    protected void checkAccess()
        throws EFapsException
    {
        if (!ECommerce.ACTIVATE.get()) {
            LOG.error("ECommerce is not activated");
            throw new WebApplicationException("ECommerce is not activated", Response.Status.FORBIDDEN);
        }
    }

}
