package com.dft.theiconic;

import com.dft.theiconic.handler.JsonBodyHandler;
import com.dft.theiconic.model.Pagination;
import com.dft.theiconic.model.order.Order;
import com.dft.theiconic.model.order.OrdersResponse;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersApi extends TheIconicSDK {

    private static final String DEFAULT_LIMIT = "50";
    private static final String V2_ORDERS = "/v2/orders";
    private static final String V2_ORDER_ITEMS = "/v2/order-items";

    public OrdersApi(String instance, String clientId, String clientSecret) {
        super(instance, clientId, clientSecret);
    }

    public OrdersResponse getOrders(int limit, int offset) {
        String path = V2_ORDERS + "?" + "limit=" + limit + "&" + "offset=" + offset;
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<OrdersResponse> handler = new JsonBodyHandler<>(OrdersResponse.class);
        return getRequestWrapped(request, handler);
    }

    public OrdersResponse getOrders(HashMap<String, String> parameters) {
        String path = V2_ORDERS + "?" + toQueryString(parameters);
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<OrdersResponse> handler = new JsonBodyHandler<>(OrdersResponse.class);
        return getRequestWrapped(request, handler);
    }

    public OrdersResponse getOrders(String orderId) {
        String path = String.format(V2_ORDERS, orderId);
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<OrdersResponse> handler = new JsonBodyHandler<>(OrdersResponse.class);
        return getRequestWrapped(request, handler);
    }

    public OrdersResponse getOrderItems() {
        HttpRequest request = get(V2_ORDER_ITEMS);
        HttpResponse.BodyHandler<OrdersResponse> handler = new JsonBodyHandler<>(OrdersResponse.class);
        return getRequestWrapped(request, handler);
    }

    public List<Order> getOrdersAll(HashMap<String, String> parameters) {

        int iOffSet = 0;
        parameters.put("limit", DEFAULT_LIMIT);
        parameters.put("offset", "" + iOffSet);
        List<Order> orders = new ArrayList<>();
        OrdersResponse ordersResponse = getOrders(parameters);
        while (true) {
            orders.addAll(ordersResponse.getItems());
            Pagination pagination = ordersResponse.getPagination();
            if (pagination.getTotalCount() == orders.size()) break;
            parameters.put("offset", "" + orders.size());
            ordersResponse = getOrders(parameters);

        }
        return orders;
    }
}
