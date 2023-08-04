package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseDomain {
    private Date orderDate;
    private double totalPrice;
    private int status; // 0.submit ; 1.accept ; 2.shipping ; 3.success ; 4.cancle

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }
}
