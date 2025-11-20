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
package org.efaps.esjp.ecommerce.rest.dto;

import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.annotation.Generated;

@JsonDeserialize(builder = ProductDto.Builder.class)
@EFapsUUID("fb21f91b-071b-4a15-b721-e1e88b28ca1a")
@EFapsApplication("eFapsApp-ECommerce")
public class ProductDto
{

    private final String sku;
    private final String name;

    @Generated("SparkTools")
    private ProductDto(Builder builder)
    {
        this.sku = builder.sku;
        this.name = builder.name;
    }

    public String getSku()
    {
        return sku;
    }

    public String getName()
    {
        return name;
    }

    @Generated("SparkTools")
    public static Builder builder()
    {
        return new Builder();
    }

    @Generated("SparkTools")
    public static final class Builder
    {

        private String sku;
        private String name;

        private Builder()
        {
        }

        public Builder withSku(String sku)
        {
            this.sku = sku;
            return this;
        }

        public Builder withName(String name)
        {
            this.name = name;
            return this;
        }

        public ProductDto build()
        {
            return new ProductDto(this);
        }
    }
}
