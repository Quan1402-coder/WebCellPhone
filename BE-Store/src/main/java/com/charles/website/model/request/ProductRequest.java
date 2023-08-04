package com.charles.website.model.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private Double price;
    private String description;
    private Integer stock;
    private Long brandId;
}
