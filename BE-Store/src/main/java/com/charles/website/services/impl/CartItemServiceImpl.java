package com.charles.website.services.impl;

import com.charles.website.entity.CartItem;
import com.charles.website.entity.Product;
import com.charles.website.entity.User;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.request.CartItemRequest;
import com.charles.website.repository.CartItemRepository;
import com.charles.website.repository.ProductRepository;
import com.charles.website.repository.UserRepository;
import com.charles.website.services.CartItemService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void add(CartItemRequest request) {
        if(request.getQuantity()==null || request.getProductId()==null)
            throw new BadRequestException(400, "Please input full info");

        CartItem cartItem = new CartItem();

        User user = userRepository.findByUsername(Authen.username());
        cartItem.setUser(user);

        Product product = productRepository.findById(request.getProductId()).get();
        if(product==null) throw new NotFoundException(404, "Product is not found");

        List<CartItem> cartItems = cartItemRepository.findAllByUserAndOrderIsNull(user);
        AtomicBoolean fl = new AtomicBoolean(true);
        cartItems.forEach(item -> {
            if(item.getId()==product.getId()) {
                fl.set(false);
                CartItem cartItem1 = cartItemRepository.findByProductAndUser(product, user);
                cartItem1.setQuantity(cartItem1.getQuantity() + request.getQuantity());
                cartItem1.setPrice(cartItem1.getQuantity() * cartItem1.getProduct().getPrice());

                if(!product.hasStock(cartItem1.getQuantity() + request.getQuantity())) {
                    throw new NotFoundException(404, "Product is not stock");
                }
                cartItemRepository.save(cartItem1);
            }
        });

        if(fl.get()) {
            if(!product.hasStock(request.getQuantity())) {
                throw new NotFoundException(404, "Product is not stock");
            }
            cartItem.setQuantity(request.getQuantity());
            cartItem.setProduct(product);
            cartItem.setPrice(product.getPrice() * cartItem.getQuantity());
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public void update(Long id, CartItemRequest request) {
        CartItem cartItem = cartItemRepository.findById(id).get();
        if(cartItem==null) throw new NotFoundException(404, "Cart item is not found");

        if(request.getQuantity()!=null) {
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(request.getQuantity()*cartItem.getProduct().getPrice());
        }

        cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).get();
        if(cartItem==null) throw new NotFoundException(404, "Cart item is not found");

        cartItemRepository.delete(cartItem);
    }

    @Override
    public List<CartItem> getList() {
        User user = userRepository.findByUsername(Authen.username());

        return cartItemRepository.findAllByUserAndOrderIsNull(user);
    }

    @Override
    public CartItem getDetail(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).get();
        if(cartItem==null) throw new NotFoundException(404, "Cart item is not found");

        return cartItem;
    }
}
