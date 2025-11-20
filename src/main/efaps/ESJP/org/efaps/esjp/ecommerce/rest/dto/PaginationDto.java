package org.efaps.esjp.ecommerce.rest.dto;

import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.annotation.Generated;

@EFapsUUID("f585c64a-7155-42f0-a715-63a8d7d53d75")
@EFapsApplication("eFapsApp-ECommerce")
@JsonDeserialize(builder = PaginationDto.Builder.class)
public class PaginationDto
{

    private final int page;
    private final int limit;
    private final int total;

    @Generated("SparkTools")
    private PaginationDto(Builder builder)
    {
        this.page = builder.page;
        this.limit = builder.limit;
        this.total = builder.total;
    }

    public int getPage()
    {
        return page;
    }

    public int getLimit()
    {
        return limit;
    }

    public int getTotal()
    {
        return total;
    }

    @Generated("SparkTools")
    public static Builder builder()
    {
        return new Builder();
    }

    @Generated("SparkTools")
    public static final class Builder
    {

        private int page;
        private int limit;
        private int total;

        private Builder()
        {
        }

        public Builder withPage(int page)
        {
            this.page = page;
            return this;
        }

        public Builder withLimit(int limit)
        {
            this.limit = limit;
            return this;
        }

        public Builder withTotal(int total)
        {
            this.total = total;
            return this;
        }

        public PaginationDto build()
        {
            return new PaginationDto(this);
        }
    }
}
