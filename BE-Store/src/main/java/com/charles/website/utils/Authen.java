package com.charles.website.utils;

import com.charles.website.entity.User;
import com.charles.website.exception.BadRequestException;
import com.charles.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Authen {

    public static void check(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println("Username: " + username);
        if(username == null || username.equals("anonymousUser")){
            throw new BadRequestException(400, "User is not login");
        }
    }

    public static String username() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if(username == null || username.equals("anonymousUser")){
            throw new BadRequestException(400, "User is not login");
        }

        return username;
    }
}
