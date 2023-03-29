package com.dft.theiconic.model.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderPayload {

    @JsonProperty("OrderId")
    private String orderId;

    @JsonProperty("OrderNr")
    private String orderNr;
}