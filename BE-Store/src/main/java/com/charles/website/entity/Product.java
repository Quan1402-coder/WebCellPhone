package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseDomain{
    private String name;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    private int stock;
    private int status; // 0.submit ; 1.accept ; 2.cancel

    @ManyToOne()
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public boolean hasStock(int amount) {
        return (this.getStock() > 0) && (amount <= this.getStock());
    }

    public void decreaseStock(int amount) {
        this.stock -= amount;
    }

    public void increaseStock(int amount){
        this.stock += amount;
    }
}
