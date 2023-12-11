package com.dft.theiconic.model.productset;

import lombok.Data;

@Data
public class ConsignmentStock {
    private String id;
    private String productId;
    private String received;
    private String quarantined;
    private String defective;
    private String canceled;
    private String returned;
    private String failed;
}