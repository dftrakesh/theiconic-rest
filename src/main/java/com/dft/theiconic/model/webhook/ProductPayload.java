package com.dft.theiconic.model.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductPayload {

    @JsonProperty("SellerSkus")
    public List<String> sellerSkus;
}
