package com.dft.theiconic.model.webhook;

import lombok.Data;

import java.util.List;

@Data
public class Item {
    public String publicId;
    public int sellerId;
    public String callbackUrl;
    public String creationSource;
    public String createdAt;
    public String updatedAt;
    public List<Event> events;
}