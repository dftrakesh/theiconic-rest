package com.dft.theiconic.model.productset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String id;
    private String uuid;
    private String srcId;
    private String name;
    private String parentSku;
    private String createdAt;
    private String updatedAt;
    private String description;
    private String brandId;
    private String primaryCategoryId;
    private String attributeSetId;
    private String sellerId;
    private String sizeSystem;
    private String sellerSku;

}
