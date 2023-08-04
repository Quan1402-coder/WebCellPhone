package com.charles.website.model.request;

import lombok.Data;

@Data
public class CartItemRequest {
    private Integer quantity;
    private Long productId;
}
