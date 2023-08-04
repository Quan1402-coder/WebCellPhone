package com.charles.website.services.impl;

import com.charles.website.entity.Brand;
import com.charles.website.entity.Product;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.request.ProductRequest;
import com.charles.website.repository.BrandRepository;
import com.charles.website.repository.ProductRepository;
import com.charles.website.services.ProductService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public void add(ProductRequest request, MultipartFile image) throws IOException {
        if(request.getName()==null || request.getPrice()==null || request.getDescription()==null ||
                request.getStock()==null || request.getBrandId()==null || image == null)
            throw new BadRequestException(400, "Please input full info");

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setStatus(0);
        String imageName = saveImage(image);
        product.setImage(imageName);

        Brand brand = brandRepository.findById(request.getBrandId()).get();
        if(brand==null) throw new NotFoundException(404, "Brand is not found");
        product.setBrand(brand);

        productRepository.save(product);
    }

    @Override
    public void update(Long id, ProductRequest request, MultipartFile image) throws IOException {
        Product product = productRepository.findById(id).get();
        if(product==null) throw new NotFoundException(404, "Product is not found");

        if(request.getName()!=null) product.setName(request.getName());
        if(request.getPrice()!=null) product.setPrice(request.getPrice());
        if(request.getDescription()!=null) product.setDescription(request.getDescription());
        if(request.getStock()!=null) product.setStock(request.getStock());
        product.setStatus(0);
        if(image!=null) {
            String imageName = saveImage(image);
            product.setImage(imageName);
        }

        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).get();
        if(product==null) throw new NotFoundException(404, "Product is not found");

        productRepository.delete(product);
    }

    @Override
    public List<Product> getList(int status) {
        if(status>=0 && status<=2) return productRepository.findAllByStatus(status);

        return productRepository.findAll();
    }

    @Override
    public Product getDetail(Long id) {
        Product product = productRepository.findById(id).get();
        if(product==null) throw new NotFoundException(404, "Product is not found");

        return product;
    }

    @Override
    public byte[] getProductImageById(Long id) throws IOException {
        Product product = productRepository.findById(id).get();

        if(product==null) throw new NotFoundException(404, "Product is not found");

        // InputStream imageStream = new FileInputStream("src/main/resources/static/images/" + product.getImage());

        return FileUtils.readFileToByteArray(new File("src/main/resources/images/" + product.getImage()));
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = productRepository.findById(id).get();
        if(product==null) throw new NotFoundException(404, "Product is not found");

        if(status==null) throw new BadRequestException(400, "Plese input status");

        product.setStatus(status);

        productRepository.save(product);
    }

    private String saveImage(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String imageName = UUID.randomUUID().toString() + "." + extension;
        File file = new File("src/main/resources/images/" + imageName);
        FileUtils.writeByteArrayToFile(file, image.getBytes());
        return imageName;
    }
}
