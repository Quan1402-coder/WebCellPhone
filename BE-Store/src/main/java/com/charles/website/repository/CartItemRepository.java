package com.charles.website.repository;

import com.charles.website.entity.CartItem;
import com.charles.website.entity.Product;
import com.charles.website.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findById(Long id);

    List<CartItem> findAllByUserAndOrderIsNull(User user);

    CartItem findByProductAndUser(Product product, User user);
}
