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
public class OrderItem {
    private String id;
    private String srcId;
    private String sellerId;
    private String orderId;
    private String uuid;
    private String status;
    private Boolean isProcessable;
    private FailureReason failureReason;
    private Shipment shipment;
    private String invoiceNumber;
    private String invoiceAccesskey;
    private String inTransit;
    private String premium;
    private String targetToShipAt;
    private Product product;
    private Double unitPrice;
    private Double taxAmount;
    private Double taxPercent;
    private Double paidPrice;
    private Double paidCommission;
    private Double shippingFee;
    private Double shippingServiceCost;
    private Double walletCredits;
    private Double storeCredits;
    private Double shippingVoucherAmount;
    private Double priceAfterDiscount;
    private Double salesDueAmount;
    private String itemSerialNumber;
    private String abatementRate;
    private String exciseRate;
    private String hsnCode;
    private String codCollectableAmount;
    private Purchase purchase;
    private String createdAt;
    private String importedAt;
    private String updatedAt;
    private String lastStatusChangedAt;
    private String warehouseName;
    private String extraAttributes;
    private Boolean isHybrid;
    private Boolean isOutlet;
}






