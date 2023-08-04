package com.charles.website.controller.admin;

import com.charles.website.model.response.UserResponse;
import com.charles.website.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AuthControllerAdmin {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ApiOperation("Return all user in website")
    public ResponseEntity<?> getAllUser(){
        List<UserResponse> listUserResponse = userService.getListUser();
        return ResponseEntity.ok(listUserResponse);
    }
}
