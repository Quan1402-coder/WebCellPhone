package com.charles.website.services;

import com.charles.website.entity.CartItem;
import com.charles.website.entity.Order;
import com.charles.website.model.request.CartItemRequest;
import com.charles.website.model.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void add(OrderRequest request);

    void updateStatus(Long id, Integer status);

    void delete(Long id);

    List<Order> getList();

    List<Order> getListByUser();

    Order getDetail(Long id);
}
