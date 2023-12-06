package com.dft.theiconic.model.webhook;

import lombok.Data;

import java.util.List;

@Data
public class WebhookRequest {
    public String callbackUrl;
    public List<String> events;
}