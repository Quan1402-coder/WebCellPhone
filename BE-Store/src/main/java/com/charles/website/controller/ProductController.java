package com.charles.website.controller;

import com.charles.website.entity.Product;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.dto.ProductDTO;
import com.charles.website.model.request.ProductRequest;
import com.charles.website.services.ProductService;
import com.charles.website.utils.Authen;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping(value = "/create", headers = { "content-type=multipart/mixed", "content-type=multipart/form-data" })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> createProduct(@RequestParam("data") String data,
                                           @RequestParam("image") MultipartFile image) throws IOException {
        Authen.check();
        Authen.check();
        ObjectMapper mapper = new ObjectMapper();
        ProductRequest req = mapper.readValue(data, ProductRequest.class);

        productService.add(req, image);
        return ResponseEntity.ok(new MessageResponse("Create product is success"));
    }


    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> updateProduct(@RequestParam Long id,
                                           @RequestBody ProductRequest request,
                                           @RequestParam("image") MultipartFile image) throws IOException {
        Authen.check();

        productService.update(id, request, image);
        return ResponseEntity.ok(new MessageResponse("Create product is success"));
    }

    @GetMapping("/views")
    public ResponseEntity<?> getAllProductViews() {
        List<Product> list = productService.getList(1);
        List<ProductDTO> productDTOList = list.stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
        return ResponseEntity.ok(productDTOList);
    }

    @GetMapping("/list-submit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> getAllProductSubmit() {
        Authen.check();

        List<Product> list = productService.getList(0);
        List<ProductDTO> productDTOList = list.stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
        return ResponseEntity.ok(productDTOList);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> getAllProductAll() {
        Authen.check();

        List<Product> list = productService.getList(3);
        List<ProductDTO> productDTOList = list.stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
        return ResponseEntity.ok(productDTOList);
    }

    @PostMapping("/update-status")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStatus(@RequestParam Long id,
                                          @RequestParam Integer status) {
        Authen.check();

        productService.updateStatus(id, status);
        return ResponseEntity.ok(new MessageResponse("Update status is success"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getDetail(id);
        return ResponseEntity.ok(new ProductDTO(product));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImageById(@PathVariable Long id) throws IOException {
        byte[] imageData = productService.getProductImageById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStatus(@RequestParam Long id) {
        Authen.check();

        productService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Delete product is success"));
    }
}
