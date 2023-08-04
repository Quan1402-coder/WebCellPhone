package com.charles.website.services;

import com.charles.website.entity.Brand;
import com.charles.website.entity.Product;
import com.charles.website.model.request.ProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {
    void add(ProductRequest request, MultipartFile image) throws IOException;

    void update(Long id, ProductRequest request, MultipartFile image) throws IOException;

    void delete(Long id);

    List<Product> getList(int status);

    Product getDetail(Long id);

    byte[] getProductImageById(Long id) throws IOException;

    void updateStatus(Long id, Integer status);
}
