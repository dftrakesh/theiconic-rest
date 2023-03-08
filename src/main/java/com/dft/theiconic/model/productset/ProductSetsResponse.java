package com.dft.theiconic.model.productset;

import com.dft.theiconic.model.Pagination;
import lombok.Data;

import java.util.List;

@Data
public class ProductSetsResponse {
    private List<Item> items;
    private Pagination pagination;

}
