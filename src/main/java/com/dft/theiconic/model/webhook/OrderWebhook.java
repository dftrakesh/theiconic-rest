package com.dft.theiconic.model.webhook;

import lombok.Data;

@Data
public class OrderWebhook {
    private String event;
    private OrderPayload payload;
}