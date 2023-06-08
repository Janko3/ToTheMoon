package com.example.tothemoon.security;

import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.UserDTO;
import com.example.tothemoon.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebSecurity {
    @Autowired
    private UserService userService;


    public boolean checkClubId(Authentication authentication, HttpServletRequest request, int id) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findUserByUsername(userDetails.getUsername());

        if(id == user.getId()) {
            return true;
        }
        return false;
    }
}
