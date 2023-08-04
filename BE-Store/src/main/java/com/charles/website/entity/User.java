package com.charles.website.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@Table(	name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User extends BaseDomain{

    @Column(updatable = false, nullable = false)
    private String email;

    @Column(updatable = false, nullable = false)
    private String username;

    @NotNull
    private String password;
    private String fullName;
    private Date birthday;
    private String numberPhone;
    private boolean active;
    private String registerToken;
    private LocalDateTime timeRegisterToken;
    private String resetPasswordToken;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> address;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public void addAddress(Address address){
        this.address.add(address);
    }
}
