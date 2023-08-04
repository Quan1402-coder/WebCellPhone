package com.charles.website.controller;

import com.charles.website.entity.Address;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.request.AddressRequest;
import com.charles.website.model.response.AddressResponse;
import com.charles.website.services.AddressService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public ResponseEntity<?> getAllAddress(){
        Authen.check();
        List<AddressResponse> listAllAddress = addressService.getAllAddress();

        return ResponseEntity.ok(listAllAddress);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody AddressRequest address){
        Authen.check();
        addressService.addAddress(address);

        return ResponseEntity.ok(new MessageResponse("Thêm địa chỉ thành công"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAddress(@RequestParam("id") Long id){
        Authen.check();
        addressService.deleteAddress(id);

        return ResponseEntity.ok(new MessageResponse("Xoá địa chỉ thành công"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> editAddress(@RequestParam("id") Long id, @RequestBody AddressRequest address){
        Authen.check();
        addressService.updateAddress(id, address);

        return ResponseEntity.ok(new MessageResponse("Sửa địa chỉ thành công"));
    }
}
