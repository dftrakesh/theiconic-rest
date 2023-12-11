package com.dft.theiconic.model.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String uuid;
    private String invoiceRequired;
    private String id;
    private String sellerId;
    private String number;
    private String nationalRegistrationNumber;
    private Boolean payoutPending;
    private Customer customer;
    private Address address;
    private Gift gift;
    private Voucher voucher;
    private String deliveryInfo;
    private String paymentMethod;
    private String currency;
    private String remarks;
    private String createdAt;
    private String importedAt;
    private String updatedAt;
    private String addressUpdatedAt;
    private String exchangeByOrderId;
    private String exchangeForOrderId;
    private String source;
    private HashMap<String, String> extraAttributes;
    private StatusList statusList;
    private String itemCount;
    private String unitPriceSumWithFees;
    private String shipmentProviderType;
    private String shipmentProviderPreSelected;
    private String targetToShip;
    private String packedItemsCount;
    private List<String> orderItemIds;
    private List<OrderItem> items;
    private String regionId;
}



