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
