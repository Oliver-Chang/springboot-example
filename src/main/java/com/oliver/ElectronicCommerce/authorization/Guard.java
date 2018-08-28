package com.oliver.ElectronicCommerce.authorization;


import com.oliver.ElectronicCommerce.user.User;
import com.oliver.ElectronicCommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class Guard {
    @Autowired
    private UserRepository userRepository;

    public boolean checkUserId(Authentication authentication, Long userId) {
        String name = authentication.getName();
        System.out.println(name + " at " + userId);
        User result = userRepository.getOne(Long.valueOf(name));
        if (result == null)
            return false;
        return result.getId() == userId;
    }
}