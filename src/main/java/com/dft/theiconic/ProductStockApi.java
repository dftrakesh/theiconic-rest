package com.dft.theiconic;

import com.dft.theiconic.handler.JsonBodyHandler;
import com.dft.theiconic.model.productset.ConsignmentStock;
import com.dft.theiconic.model.productset.ProductStock;
import com.dft.theiconic.model.productset.ProductStocks;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ProductStockApi extends TheIconicSDK {

    private static final String V2_PRODUCT_STOCK_PRODUCT = "/v2/stock/product";
    private static final String V2_PRODUCT_STOCK_BY_PRODUCT = "/v2/stock/product/%s";
    private static final String V2_PRODUCT_STOCK_BY_PRODUCT_SET = "/v2/stock/product-set/%s";
    private static final String V2_PRODUCTS_CONSIGNMENT_STOCKS = "/v2/products/%s/consignment-stocks";

    public ProductStockApi(String instance, String clientId, String clientSecret) {
        super(instance, clientId, clientSecret);
    }

    public ProductStock getProductStock(String productId) {
        String path = String.format(V2_PRODUCT_STOCK_BY_PRODUCT, productId);
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<ProductStock> handler = new JsonBodyHandler<>(ProductStock.class);
        return getRequestWrapped(request, handler);
    }

    public ProductStocks getProductSetStock(String productSetId) {
        String path = String.format(V2_PRODUCT_STOCK_BY_PRODUCT_SET, productSetId);
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<ProductStocks> handler = new JsonBodyHandler<>(ProductStocks.class);
        return getRequestWrapped(request, handler);
    }

    public ProductStocks updateStock(String productId, Integer quantity) {
        ProductStock productStock = new ProductStock();
        productStock.setProductId(productId);
        productStock.setQuantity(quantity);
        return updateStock(List.of(productStock));
    }

    public ConsignmentStock getConsignmentStocks(String productId) {
        String path = String.format(V2_PRODUCTS_CONSIGNMENT_STOCKS, productId);
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<ConsignmentStock> handler = new JsonBodyHandler<>(ConsignmentStock.class);
        return getRequestWrapped(request, handler);
    }

    public ProductStocks updateStock(List<ProductStock> productStocks) {
        HttpRequest request = put(V2_PRODUCT_STOCK_PRODUCT, getString(productStocks));
        HttpResponse.BodyHandler<ProductStocks> handler = new JsonBodyHandler<>(ProductStocks.class);
        return getRequestWrapped(request, handler);
    }
}
