package com.charles.website.controller;

import com.charles.website.entity.Brand;
import com.charles.website.model.MessageResponse;
import com.charles.website.services.BrandService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public ResponseEntity<?> getAllBrand() {

        return ResponseEntity.ok(brandService.getList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> getDetailBrand(@PathVariable("id")Long id) {
        Authen.check();

        return ResponseEntity.ok(brandService.getDetail(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> createBrand(@RequestBody Brand request) {
        Authen.check();
        brandService.add(request);
        return ResponseEntity.ok(new MessageResponse("Create brand is success"));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> updateBrand(@RequestParam("id")Long id,
                                         @RequestBody Brand request) {
        Authen.check();
        brandService.update(id, request);
        return ResponseEntity.ok(new MessageResponse("Update brand is success"));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> deleteBrand(@RequestParam("id")Long id) {
        Authen.check();
        brandService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Delete brand is success"));
    }
}
