package com.charles.website.model.dto;

import com.charles.website.entity.Brand;
import com.charles.website.entity.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String description;
    private int stock;
    private int status;
    private Brand brand;
    private List<CommentDTO> comment;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.status = product.getStatus();
        this.brand = product.getBrand();
        this.comment = product.getComments().stream().map(cmt -> new CommentDTO(cmt)).collect(Collectors.toList());
    }
}
