package com.dft.theiconic.model.productset;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ProductStocks extends ArrayList<ProductStock> {

}
