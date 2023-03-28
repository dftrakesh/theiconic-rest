package com.dft.theiconic.model.webhook;

import lombok.Data;

@Data
public class OrderItemWebhook {
    private String event;
    private OrderItemPayload payload;
}