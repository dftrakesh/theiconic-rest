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
public class Product {
    private String id;
    private String uuid;
    private String srcId;
    private String shopSku;
    private String sellerSku;
    private String sin;
    private String sellerId;
    private String productSetId;
    private String productIdentifier;
    private String approvalStatus;
    private String variation;
    private String updatedByUserId;
    private String status;
    private String taxClassId;
    private String shipmentTypeId;
    private String duplicateGroupId;
    private String srcUpdatedAt;
    private String createdAt;
    private String updatedAt;
}
