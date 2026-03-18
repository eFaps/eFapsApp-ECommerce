package org.efaps.esjp.ecommerce.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.efaps.admin.program.esjp.EFapsApplication;
import org.efaps.admin.program.esjp.EFapsUUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.annotation.Generated;

@EFapsUUID("34a19812-e7a5-4e3c-8b1a-c6c2c994850b")
@EFapsApplication("eFapsApp-ECommerce")
@JsonDeserialize(builder = ImageDto.Builder.class)
public class ImageDto
{

    private final String oid;
    private final String caption;
    private final ImageType type;

    @Generated("SparkTools")
    private ImageDto(Builder builder)
    {
        this.oid = builder.oid;
        this.caption = builder.caption;
        this.type = builder.type;
    }

    public String getOid()
    {
        return oid;
    }

    public String getCaption()
    {
        return caption;
    }

    public ImageType getType()
    {
        return type;
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
        private String caption;
        private ImageType type;

        private Builder()
        {
        }

        public Builder withOid(String oid)
        {
            this.oid = oid;
            return this;
        }

        public Builder withCaption(String caption)
        {
            this.caption = caption;
            return this;
        }

        public Builder withType(ImageType type)
        {
            this.type = type;
            return this;
        }

        public ImageDto build()
        {
            return new ImageDto(this);
        }
    }
}
