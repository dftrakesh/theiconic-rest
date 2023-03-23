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
public class ProductStock {
    private String productId;
    private String sellerSku;
    private String shopSku;
    private String name;
    private Integer quantity;
    private Integer reservedStock;
    private Integer preVerificationStock;
    private Integer available;
}
