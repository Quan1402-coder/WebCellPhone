package com.charles.website.model.dto;

import com.charles.website.entity.CartItem;
import com.charles.website.model.response.UserResponse;
import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private ProductDTO product;
    private UserResponse user;
    private double price;
    private int quantity;

    public CartItemDTO(CartItem cartItem) {
        this.id = cartItem.getId();
        product = new ProductDTO(cartItem.getProduct());
        user = new UserResponse(cartItem.getUser());
        price = cartItem.getPrice();
        quantity = cartItem.getQuantity();
    }
}
