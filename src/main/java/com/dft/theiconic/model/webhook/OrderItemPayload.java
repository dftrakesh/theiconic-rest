package com.dft.theiconic.model.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderItemPayload {

    @JsonProperty("OrderId")
    private Long orderId;

    @JsonProperty("OrderItemIds")
    private List<Long> orderItemIdList;

    @JsonProperty("NewStatus")
    private String status;
}