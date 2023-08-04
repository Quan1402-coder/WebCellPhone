package com.charles.website.model.dto;

import com.charles.website.entity.User;
//import com.charles.website.model.AbstractDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Data
public class RegisterDTO{
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Date birthday;
    private String phoneNumber;
}
