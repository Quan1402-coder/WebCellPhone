package com.charles.website.controller;

import com.charles.website.model.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-full")
public class Test {
    @GetMapping("")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok(new MessageResponse("Test thu"));
    }
}
