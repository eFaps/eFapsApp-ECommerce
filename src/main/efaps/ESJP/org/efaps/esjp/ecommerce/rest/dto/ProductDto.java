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
