package com.dft.theiconic.model.webhook;

import com.dft.theiconic.model.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookResponse {
    public String webhookId;

    @JsonDeserialize(using = DateDeserializer.class)
    public LocalDateTime createdAt;
}