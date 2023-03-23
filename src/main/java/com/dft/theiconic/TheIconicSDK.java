package com.dft.theiconic;

import com.dft.theiconic.handler.JsonBodyHandler;
import com.dft.theiconic.model.AccessCredentials;
import com.dft.theiconic.model.order.OrdersResponse;
import com.dft.theiconic.model.productset.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class TheIconicSDK {

    private static final String ACCEPT = "Accept";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";
    private static final String BEARER = "Bearer ";
    private static final String APPLICATION_JSON = "application/json";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String HTTP_HEADER_VALUE_APPLICATION_FORM_URL_ENCODED = "application/x-www-form-urlencoded";

    private final String clientId;
    private final String clientSecret;
    private final ObjectMapper objectMapper;

    private String accessToken;
    private LocalDateTime expireAt;
    private final HttpClient client;
    private final String baseUrl;
    private final String instance;

    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 60000;

    protected TheIconicSDK(String instance, String clientId, String clientSecret) {
        this.instance = instance;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        this.baseUrl = "https://sellercenter-api." + instance;
        this.expireAt = LocalDateTime.now().minusHours(1);
        this.objectMapper = new ObjectMapper();
    }


    protected HttpRequest get(String path) {

        if (LocalDateTime.now().isAfter(expireAt)) {
            refreshAccessToken();
        }
        return HttpRequest.newBuilder(URI.create(baseUrl + path))
                .header(AUTHORIZATION, BEARER + accessToken)
                .header(ACCEPT, APPLICATION_JSON)
                .GET()
                .build();
    }

    @SneakyThrows
    protected String getString(Object body) {
        return objectMapper.writeValueAsString(body);
    }

    @SneakyThrows
    private void refreshAccessToken() {
        Map<Object, Object> parameters = new HashMap<>();
        parameters.put(GRANT_TYPE, CLIENT_CREDENTIALS);

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://sellercenter." + this.instance + "/oauth/client-credentials"))
                .header(AUTHORIZATION, BASIC + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()))
                .header(CONTENT_TYPE, HTTP_HEADER_VALUE_APPLICATION_FORM_URL_ENCODED)
                .POST(ofFormData(parameters))
                .build();

        HttpResponse.BodyHandler<AccessCredentials> handler = new JsonBodyHandler<>(AccessCredentials.class);
        AccessCredentials accessCredentials = getRequestWrapped(request, handler);
        this.accessToken = accessCredentials.getAccessToken();
        this.expireAt = LocalDateTime.now().plusSeconds(accessCredentials.getExpiresIn() - 10);
    }

    public HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    @SneakyThrows
    protected HttpRequest post(String path, final String jsonBody) {
        if (LocalDateTime.now().isAfter(expireAt)) {
            refreshAccessToken();
        }
        return HttpRequest.newBuilder(URI.create(baseUrl + path))
                .header(AUTHORIZATION, BEARER + this.accessToken)
                .header(ACCEPT, APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
    }

    @SneakyThrows
    protected HttpRequest put(String path, final String jsonBody) {
        if (LocalDateTime.now().isAfter(expireAt)) {
            refreshAccessToken();
        }
        return HttpRequest.newBuilder(URI.create(baseUrl + path))
                .header(AUTHORIZATION, BEARER + this.accessToken)
                .header(ACCEPT, APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
    }

    @SneakyThrows
    protected HttpRequest delete(String path) {
        if (LocalDateTime.now().isAfter(expireAt)) {
            refreshAccessToken();
        }
        return HttpRequest.newBuilder(URI.create(baseUrl + path))
                .header(AUTHORIZATION, BEARER + this.accessToken)
                .header(ACCEPT, APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .DELETE()
                .build();
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {

        return client
                .sendAsync(request, handler)
                .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
                .get()
                .body();
    }

    @SneakyThrows
    public <T> CompletableFuture<HttpResponse<T>> tryResend(HttpClient client,
                                                            HttpRequest request,
                                                            HttpResponse.BodyHandler<T> handler,
                                                            HttpResponse<T> resp, int count) {
        if (resp.statusCode() == 429 && count < MAX_ATTEMPTS) {
            Thread.sleep(TIME_OUT_DURATION);
            return client.sendAsync(request, handler)
                    .thenComposeAsync(response -> tryResend(client, request, handler, response, count + 1));
        }
        return CompletableFuture.completedFuture(resp);
    }
}