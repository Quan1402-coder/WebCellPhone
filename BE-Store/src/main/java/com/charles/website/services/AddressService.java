package com.charles.website.services;

import com.charles.website.entity.Address;
import com.charles.website.model.request.AddressRequest;
import com.charles.website.model.response.AddressResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    public List<AddressResponse> getAllAddress();

    public void addAddress(AddressRequest address);

    public void deleteAddress(Long id);

    public void updateAddress(Long id, AddressRequest addressNew);
}
