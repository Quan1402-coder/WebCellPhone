package com.charles.website.services.impl;

import com.charles.website.entity.Brand;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.repository.BrandRepository;
import com.charles.website.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public void add(Brand request) {
        if(request.getName()==null || request.getDescription()==null)
            throw new BadRequestException(400, "Please input full info");

        brandRepository.save(request);
    }

    @Override
    public void update(Long id, Brand request) {
        Brand brand = brandRepository.findById(id).get();
        if(brand==null) {
            throw new NotFoundException(404, "Brand is not found");
        }

        if(request.getName()!=null) brand.setName(request.getName());
        if(request.getDescription()!=null) brand.setDescription(request.getDescription());

        brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        Brand brand = brandRepository.findById(id).get();
        if(brand==null) {
            throw new NotFoundException(404, "Brand is not found");
        }

        brandRepository.delete(brand);
    }

    @Override
    public List<Brand> getList() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getDetail(Long id) {
        Brand brand = brandRepository.findById(id).get();
        if(brand==null) {
            throw new NotFoundException(404, "Brand is not found");
        }

        return brand;
    }
}
