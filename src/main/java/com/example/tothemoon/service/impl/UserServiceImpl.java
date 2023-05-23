package com.example.tothemoon.service.impl;

import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.RegisterDTO;
import com.example.tothemoon.model.enums.EUserType;
import com.example.tothemoon.repository.UserRepository;
import com.example.tothemoon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email){

        Optional<User> user = userRepository.findFirstByEmail(email);
        if(!user.isEmpty()){
            return user.get();
        }
        return null;
    }
    @Override
    public User createUser(RegisterDTO registerDTO){
        Optional<User> user = userRepository.findFirstByEmail(registerDTO.getEmail());
        if(user.isPresent()){
            return null;
        }
        User newUser = new User();
        newUser.setId(registerDTO.getId());
        newUser.setUsername(registerDTO.getUsername());
        newUser.setEmail(registerDTO.getEmail());
        newUser.setFirstName(registerDTO.getFirstName());
        newUser.setLastName(registerDTO.getLastName());
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        newUser.setRole(EUserType.USER);
        newUser = userRepository.save(newUser);
        return newUser;
    }
    @Override
    public List<User>findAll(){
        return this.userRepository.findAll();
    }
    @Override
    public User findById(int id){
       Optional<User> user = userRepository.findById(id);
       if(!user.isPresent()){
           return null;
       }
       return user.get();
    }
}
