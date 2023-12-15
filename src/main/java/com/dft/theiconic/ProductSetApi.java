package com.dft.theiconic;

import com.dft.theiconic.handler.JsonBodyHandler;
import com.dft.theiconic.model.Pagination;
import com.dft.theiconic.model.productset.*;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ProductSetApi extends TheIconicSDK {

    private static final int DEFAULT_LIMIT = 50;
    private static final String V2_PRODUCT_SETS = "/v2/product-sets";
    private static final String V2_PRODUCT_SET = "/v2/product-set/";
    private static final String V2_PRODUCT_SHOP_SKU = "/v2/product/shop-sku/%s";
    private static final String V2_STOCK_PRODUCT_SET = "/v2/stock/product-set/%s";
    private static final String V2_PRODUCT_SELLER_SKU = "/v2/product/seller-sku/%s";
    private static final String V2_PRODUCT_SET_PRODUCTS = "/v2/product-set/%s/products";

    public ProductSetApi(String instance, String clientId, String clientSecret) {
        super(instance, clientId, clientSecret);
    }

    public ProductSetsResponse getProductSets(int limit, int offset) {
        String path = V2_PRODUCT_SETS + "?" + "limit=" + limit + "&" + "offset=" + offset;
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<ProductSetsResponse> handler = new JsonBodyHandler<>(ProductSetsResponse.class);
        return getRequestWrapped(request, handler);
    }

    public Item getProductSetById(Integer productSetId) {
        String path = V2_PRODUCT_SET + productSetId;
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<Item> handler = new JsonBodyHandler<>(Item.class);
        return getRequestWrapped(request, handler);
    }

    public Products getProducts(String productSetId) {
        String path = String.format(V2_PRODUCT_SET_PRODUCTS, productSetId);
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<Products> handler = new JsonBodyHandler<>(Products.class);
        return getRequestWrapped(request, handler);
    }

    public ProductStocks getProductSetStock(String productSetId) {
        String path = String.format(V2_STOCK_PRODUCT_SET, productSetId);
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<ProductStocks> handler = new JsonBodyHandler<>(ProductStocks.class);
        return getRequestWrapped(request, handler);
    }

    public Product getProductByShopSku(String shopSku) {
        String path = String.format(V2_PRODUCT_SHOP_SKU, shopSku);
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<Product> handler = new JsonBodyHandler<>(Product.class);
        return getRequestWrapped(request, handler);
    }

    public Product getProductBySellerSku(String sellerSku) {
        String path = String.format(V2_PRODUCT_SELLER_SKU, sellerSku);
        HttpRequest request = get(path);
        HttpResponse.BodyHandler<Product> handler = new JsonBodyHandler<>(Product.class);
        return getRequestWrapped(request, handler);
    }

    public List<Item> getAllProductSetsAll() {
        int iOffSet = 0;
        List<Item> items = new ArrayList<>();
        ProductSetsResponse productSetsResponse = getProductSets(DEFAULT_LIMIT, iOffSet);
        while (true) {
            items.addAll(productSetsResponse.getItems());
            Pagination pagination = productSetsResponse.getPagination();
            if (pagination.getTotalCount() == items.size()) break;
            productSetsResponse = getProductSets(DEFAULT_LIMIT, items.size());

        }
        return items;
    }

}
