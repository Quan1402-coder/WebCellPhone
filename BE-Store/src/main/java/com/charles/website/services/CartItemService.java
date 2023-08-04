package com.charles.website.services;

import com.charles.website.entity.CartItem;
import com.charles.website.model.request.CartItemRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {
    void add(CartItemRequest request);

    void update(Long id, CartItemRequest request);

    void delete(Long id);

    List<CartItem> getList();

    CartItem getDetail(Long id);
}
