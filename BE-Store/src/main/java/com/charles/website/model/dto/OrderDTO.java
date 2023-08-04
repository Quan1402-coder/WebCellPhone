package com.charles.website.model.dto;

import com.charles.website.entity.Order;
import com.charles.website.model.response.AddressResponse;
import com.charles.website.model.response.UserResponse;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class OrderDTO {
    private UserResponse user;
    private Date orderDate;
    private double totalPrice;
    private Set<CartItemDTO> listCartItem;
    private AddressResponse address;

    public OrderDTO(Order order) {
        user = new UserResponse(order.getUser());
        orderDate = order.getOrderDate();
        totalPrice = order.getTotalPrice();
        listCartItem = order.getCartItems().stream().map(item -> new CartItemDTO(item)).collect(Collectors.toSet());
        address = new AddressResponse(order.getAddress());
    }
}
