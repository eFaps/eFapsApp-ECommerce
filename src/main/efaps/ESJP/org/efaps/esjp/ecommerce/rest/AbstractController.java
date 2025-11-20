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
