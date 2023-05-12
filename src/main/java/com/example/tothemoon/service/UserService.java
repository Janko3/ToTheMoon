package com.example.tothemoon.service;

import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.RegisterDTO;

public interface UserService {
    User findByEmail(String email);
    User createUser(RegisterDTO registerDTO);
}
