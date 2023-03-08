package com.dft.theiconic.model;

import lombok.Data;

@Data
public class Pagination {
    private int limit;
    private int offset;
    private int totalCount;
}
