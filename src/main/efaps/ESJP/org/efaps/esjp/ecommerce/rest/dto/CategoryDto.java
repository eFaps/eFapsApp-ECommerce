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

import java.util.ArrayList;
import java.util.List;

import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.annotation.Generated;

@JsonDeserialize(builder = CategoryDto.Builder.class)
@EFapsUUID("0295a9e3-8f45-4f90-9899-4ac137ae19c2")
@EFapsApplication("eFapsApp-ECommerce")
public class CategoryDto
{

    private final String oid;
    private final String name;
    private final String label;
    private final List<CategoryDto> childCategories;

    @Generated("SparkTools")
    private CategoryDto(Builder builder)
    {
        this.oid = builder.oid;
        this.name = builder.name;
        this.label = builder.label;
        this.childCategories = builder.childCategories;
    }

    public String getOid()
    {
        return oid;
    }

    public String getName()
    {
        return name;
    }

    public String getLabel()
    {
        return label;
    }

    public List<CategoryDto> getChildCategories()
    {
        return childCategories;
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
        private String name;
        private String label;
        private Long parentId;

        private List<CategoryDto> childCategories = new ArrayList<>();

        private final List<CategoryDto.Builder> childBldrs = new ArrayList<>();

        private Builder()
        {
        }

        public Long getParentId()
        {
            return parentId;
        }

        public Builder setParentId(Long parentId)
        {
            this.parentId = parentId;
            return this;
        }

        public Builder withOid(String oid)
        {
            this.oid = oid;
            return this;
        }

        public Builder withName(String name)
        {
            this.name = name;
            return this;
        }

        public Builder withLabel(String label)
        {
            this.label = label;
            return this;
        }

        public Builder withChildCategories(List<CategoryDto> childCategories)
        {
            this.childCategories = childCategories;
            return this;
        }

        public Builder addChildBdlr(CategoryDto.Builder childBldr)
        {
            this.childBldrs.add(childBldr);
            return this;
        }

        public CategoryDto build()
        {
            withChildCategories(childBldrs.stream().map(Builder::build).toList());
            return new CategoryDto(this);
        }
    }
}
