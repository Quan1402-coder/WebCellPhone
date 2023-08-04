package com.charles.website.model.response;

import com.charles.website.entity.Address;
import com.charles.website.entity.User;
//import com.charles.website.model.AbstractDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class UserResponse{
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Date birthday;
    private List<AddressResponse> address;

    public UserResponse() {
        address = new ArrayList<>();
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.birthday = user.getBirthday();
        address = new ArrayList<>();
        for(Address addr: user.getAddress()){
            address.add(new AddressResponse(addr));
        }
    }
}
