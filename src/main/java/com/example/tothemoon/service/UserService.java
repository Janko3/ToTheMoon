package com.example.tothemoon.service;

import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.PassChangeDto;
import com.example.tothemoon.model.dto.RegisterDTO;
import com.example.tothemoon.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO findByUsername(String username);
    User createUser(RegisterDTO registerDTO);
    List<UserDTO> findAll();
    UserDTO findById(int id);
    User findUserByUsername(String username);
    User findLoggedUser();
    UserDTO updateUser(UserDTO userDTO);
    boolean isTruePassword(int id,String password);
    public boolean changePassword(PassChangeDto passChangeDto, String newPassword);


}
