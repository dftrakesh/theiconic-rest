package com.dft.theiconic.model.webhook;

import lombok.Data;

import java.util.List;

@Data
public class WebhooksWrapper {
    public List<Item> items;
}