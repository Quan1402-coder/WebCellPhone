package com.charles.website.services;

import com.charles.website.entity.Brand;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface BrandService {
    void add(Brand request);

    void update(Long id, Brand request);

    void delete(Long id);

    List<Brand> getList();

    Brand getDetail(Long id);
}
