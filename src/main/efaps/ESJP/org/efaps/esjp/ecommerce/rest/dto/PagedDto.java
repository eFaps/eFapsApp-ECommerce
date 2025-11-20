package org.efaps.esjp.ecommerce.rest.dto;

import java.util.Collections;
import java.util.List;

import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.annotation.Generated;

@EFapsUUID("e1b8faf6-1fd4-4250-8c0c-c602a66e690b")
@EFapsApplication("eFapsApp-ECommerce")
@JsonDeserialize(builder = PagedDto.Builder.class)
public class PagedDto<T>
{

    private final List<T> content;
    private final PaginationDto pagination;

    @Generated("SparkTools")
    private PagedDto(Builder<T> builder)
    {
        this.content = builder.content;
        this.pagination = builder.pagination;
    }

    public List<T> getContent()
    {
        return content;
    }

    public PaginationDto getPagination()
    {
        return pagination;
    }

    @Generated("SparkTools")
    public static <T> Builder<T> builder()
    {
        return new Builder<>();
    }

    @Generated("SparkTools")
    public static final class Builder<T>
    {

        private List<T> content = Collections.emptyList();
        private PaginationDto pagination;

        private Builder()
        {
        }

        public Builder<T> withContent(List<T> content)
        {
            this.content = content;
            return this;
        }

        public Builder<T> withPagination(PaginationDto pagination)
        {
            this.pagination = pagination;
            return this;
        }

        public PagedDto<T> build()
        {
            return new PagedDto<>(this);
        }
    }
}
