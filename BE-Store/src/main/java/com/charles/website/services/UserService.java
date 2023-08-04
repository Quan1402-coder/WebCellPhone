package com.charles.website.services;

import com.charles.website.entity.User;
import com.charles.website.model.dto.RegisterDTO;
import com.charles.website.model.response.JwtResponse;
import com.charles.website.model.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserResponse> getListUser();

    public String createAccount(RegisterDTO registerDTO);

    public void verifyRegister(User user);

    public JwtResponse loginAccount(String username, String password);

    public void updateResetPasswordToken(String token, String email);

    public User getByResetPasswordToken(String token);

    public void updatePassword(User customer, String newPassword);

    public User getByRegisterToken(String token);

    void updateInfo(RegisterDTO request);

    User infoDetail();
}
