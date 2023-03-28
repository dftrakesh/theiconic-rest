package com.dft.theiconic.model.webhook;

import lombok.Data;

@Data
public class OrderWebhookDTO {
    private String event;
    private OrderPayload payload;
}