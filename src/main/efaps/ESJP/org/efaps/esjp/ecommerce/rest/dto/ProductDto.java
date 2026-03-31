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

import java.math.BigDecimal;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.annotation.Generated;

@JsonDeserialize(builder = ProductDto.Builder.class)
@EFapsUUID("fb21f91b-071b-4a15-b721-e1e88b28ca1a")
@EFapsApplication("eFapsApp-ECommerce")
public class ProductDto
{

    private final String oid;
    private final String sku;
    private final String name;
    private final BigDecimal netUnitPrice;
    private final BigDecimal crossUnitPrice;
    private final Set<CategoryDto> categories;
    private final Set<ImageDto> images;

    @Generated("SparkTools")
    private ProductDto(Builder builder)
    {
        this.oid = builder.oid;
        this.sku = builder.sku;
        this.name = builder.name;
        this.netUnitPrice = builder.netUnitPrice;
        this.crossUnitPrice = builder.crossUnitPrice;
        this.categories = builder.categories;
        this.images = builder.images;
    }

    public String getOid()
    {
        return oid;
    }

    public String getSku()
    {
        return sku;
    }

    public String getName()
    {
        return name;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public BigDecimal getNetUnitPrice()
    {
        return netUnitPrice;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public BigDecimal getCrossUnitPrice()
    {
        return crossUnitPrice;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<CategoryDto> getCategories()
    {
        return categories;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<ImageDto> getImages()
    {
        return images;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Generated("SparkTools")
    public static Builder builder()
    {
        return new Builder();
    }

    @Generated("SparkTools")
    public static final class Builder
    {

        private String oid;
        private String sku;
        private String name;
        private BigDecimal netUnitPrice;
        private BigDecimal crossUnitPrice;
        private Set<CategoryDto> categories;
        private Set<ImageDto> images;

        private Builder()
        {
        }

        public Builder withOid(String oid)
        {
            this.oid = oid;
            return this;
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

        public Builder withNetUnitPrice(BigDecimal netUnitPrice)
        {
            this.netUnitPrice = netUnitPrice;
            return this;
        }

        public Builder withCrossUnitPrice(BigDecimal crossUnitPrice)
        {
            this.crossUnitPrice = crossUnitPrice;
            return this;
        }


        public Builder withCategories(Set<CategoryDto> categories)
        {
            this.categories = categories;
            return this;
        }

        public Builder withImages(Set<ImageDto> images)
        {
            this.images = images;
            return this;
        }

        public ProductDto build()
        {
            return new ProductDto(this);
        }
    }
}
