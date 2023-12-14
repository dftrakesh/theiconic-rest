package com.dft.theiconic.model.webhook;

import lombok.Data;

@Data
public class ProductWebhook {

    private String event;
    private ProductPayload payload;
}
