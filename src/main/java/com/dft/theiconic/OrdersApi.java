package com.dft.theiconic;

import com.dft.theiconic.handler.JsonBodyHandler;
import com.dft.theiconic.model.order.OrdersResponse;
import com.dft.theiconic.model.productset.ProductSetsResponse;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OrdersApi extends TheIconicSDK {

    private static final String V2_ORDERS = "/v2/orders";

    public OrdersApi(String instance, String clientId, String clientSecret) {
        super(instance, clientId, clientSecret);
    }

    public OrdersResponse getOrders(int limit, int offset) {
        String path = V2_ORDERS + "?" + "limit=" + limit + "&" + "offset=" + offset;
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<OrdersResponse> handler = new JsonBodyHandler<>(OrdersResponse.class);
        return getRequestWrapped(request, handler);
    }
}
