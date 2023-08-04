package com.charles.website.services.impl;

import com.charles.website.entity.Address;
import com.charles.website.entity.User;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.UnauthorizedException;
import com.charles.website.model.request.AddressRequest;
import com.charles.website.model.response.AddressResponse;
import com.charles.website.repository.AddressRepository;
import com.charles.website.repository.UserRepository;
import com.charles.website.services.AddressService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressResponse> getAllAddress() {

        User user = userRepository.findByUsername(Authen.username());
        List<Address> addressList = addressRepository.findAllByUser(user);
        List<AddressResponse> listAddressResponse = new ArrayList<AddressResponse>();

        for(Address addr: addressList){
            listAddressResponse.add(new AddressResponse(addr));
        }

        return listAddressResponse;
    }

    @Override
    public void addAddress(AddressRequest address) {
        if(address.getCity()==null || address.getCoutry()==null ||
                address.getDistrict()==null || address.getStreet()==null)
            throw new BadRequestException(400, "Please input full info");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);

        if(user==null){
            throw new UnauthorizedException(1302, "account has not login");
        }

        Address address1 = new Address(address.getCoutry(), address.getCity(), address.getDistrict(), address.getStreet(), user);
        user.addAddress(address1);

        userRepository.save(user);
    }

    @Override
    public void deleteAddress(Long id) {
        if(!addressRepository.existsById(id)){
            throw new BadRequestException(1600, "address is not existed");
        }
        addressRepository.deleteById(id);
        System.out.println("hello");
    }

    @Override
    public void updateAddress(Long id, AddressRequest addressNew) {

        Optional<Address> addressOp = addressRepository.findById(id);

        if(!addressOp.isPresent()){
            throw new BadRequestException(1600, "address is not existed");
        }

        Address address = addressOp.get();
        if(addressNew.getCoutry()!=null)
            address.setCoutry(addressNew.getCoutry());
        if(addressNew.getCity()!=null)
            address.setCity(addressNew.getCity());
        if(addressNew.getDistrict()!=null)
            address.setDistrict(addressNew.getDistrict());
        if(addressNew.getStreet()!=null)
            address.setStreet(addressNew.getStreet());

        addressRepository.save(address);
    }

}
