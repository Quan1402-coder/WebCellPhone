package com.charles.website.services.impl;

import com.charles.website.entity.*;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.request.OrderRequest;
import com.charles.website.repository.*;
import com.charles.website.services.OrderService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void add(OrderRequest request) {
        if(request.getAddressId() == null)
            throw new BadRequestException(400, "Please input address");

        User user = userRepository.findByUsername(Authen.username());
        List<CartItem> listCartItem = cartItemRepository.findAllByUserAndOrderIsNull(user);
        if(listCartItem.size()==0) throw new BadRequestException(400, "Cart item is empty");

        Order order = new Order();
        order.setCartItems(new ArrayList<>());
        double total = 0;
        for(CartItem cartItem: listCartItem) {
            Product product = cartItem.getProduct();
            if(!product.hasStock(cartItem.getQuantity())) {
                throw new NotFoundException(404, "Product " + cartItem.getProduct().getName() + " is not stock");
            }
            product.decreaseStock(cartItem.getQuantity());
            productRepository.save(product);
            total += cartItem.getPrice();
            cartItem.setOrder(order);
            order.addItem(cartItem);
        }
        order.setTotalPrice(total);
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setUser(user);
        order.setStatus(0);

        Address address = addressRepository.findById(request.getAddressId()).get();
        if (address==null) throw new NotFoundException(404, "Address is not found");
        order.setAddress(address);

        orderRepository.save(order);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Order order = orderRepository.findById(id).get();
        if(order==null) throw new NotFoundException(404, "Order is not found");
        if(status==null) throw new BadRequestException(400, "Please input status");

        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id).get();
        if(order==null) throw new NotFoundException(404, "Order is not found");

        orderRepository.delete(order);
    }

    @Override
    public List<Order> getList() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getListByUser() {
        User user = userRepository.findByUsername(Authen.username());

        return orderRepository.findAllByUser(user);
    }

    @Override
    public Order getDetail(Long id) {
        Order order = orderRepository.findById(id).get();
        if(order==null) throw new NotFoundException(404, "Order is not found");

        return order;
    }

}