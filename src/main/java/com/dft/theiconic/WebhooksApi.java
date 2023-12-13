package com.dft.theiconic;

import com.dft.theiconic.handler.JsonBodyHandler;
import com.dft.theiconic.model.webhook.WebhookRequest;
import com.dft.theiconic.model.webhook.WebhookResponse;
import com.dft.theiconic.model.webhook.WebhooksWrapper;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebhooksApi extends TheIconicSDK {

    private static final String V2_WEBHOOKS = "/v2/webhooks";
    private static final String V2_WEBHOOK = "/v2/webhook";
    private static final String V2_WEBHOOK_ENTITIES = "/v2/webhook-entities";

    public WebhooksApi(String instance, String clientId, String clientSecret) {
        super(instance, clientId, clientSecret);
    }

    public WebhooksWrapper getWebhooks() {
        HttpRequest request = get(V2_WEBHOOKS);
        HttpResponse.BodyHandler<WebhooksWrapper> handler = new JsonBodyHandler<>(WebhooksWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public WebhooksWrapper getWebhooksEntities() {
        HttpRequest request = get(V2_WEBHOOK_ENTITIES);
        HttpResponse.BodyHandler<WebhooksWrapper> handler = new JsonBodyHandler<>(WebhooksWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public void deleteWebhook(String webhookId) {
        String path = V2_WEBHOOK + "/" + webhookId;
        HttpRequest request = delete(path);
        deleteRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }

    public WebhookResponse createWebhook(WebhookRequest webhookRequest) {
        HttpRequest request = post(V2_WEBHOOK, webhookRequest);
        HttpResponse.BodyHandler<WebhookResponse> handler = new JsonBodyHandler<>(WebhookResponse.class);
        return getRequestWrapped(request, handler);
    }
}