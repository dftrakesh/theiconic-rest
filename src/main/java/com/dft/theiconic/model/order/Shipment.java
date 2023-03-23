package com.dft.theiconic.model.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shipment {
    private String type;
    private String crossdockingDeliveryType;
    private String method;
    private String preProvider;
    private Provider provider;
    private Boolean providerPreselected;
    private String providerProduct;
    private String providerType;
    private String weight;
    private String trackingCode;
    private String preTrackingCode;
}


