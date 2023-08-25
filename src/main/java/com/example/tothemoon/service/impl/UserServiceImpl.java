package com.example.tothemoon.service.impl;

import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.PassChangeDto;
import com.example.tothemoon.model.dto.RegisterDTO;
import com.example.tothemoon.model.dto.UserDTO;
import com.example.tothemoon.model.enums.EUserType;
import com.example.tothemoon.repository.UserRepository;
import com.example.tothemoon.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper =modelMapper;
    }

    @Override
    public UserDTO findByUsername(String username){

        Optional<User> user = userRepository.findFirstByUsername(username);
        if(!user.isEmpty()){
            User userObj = user.get();
            UserDTO userDTO = modelMapper.map(userObj,UserDTO.class);
            return userDTO;
        }
        return null;
    }
    @Override
    public User createUser(RegisterDTO registerDTO){
        Optional<User> user = userRepository.findFirstByUsername(registerDTO.getUsername());
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
    public List<UserDTO>findAll(){

        List<User>users = userRepository.findAll();
        List<UserDTO>userDTOS = new ArrayList<>();
        for (User user :users){
            UserDTO userDTO = modelMapper.map(user,UserDTO.class);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
    @Override
    public UserDTO findById(int id){
       Optional<User> user = userRepository.findById(id);
       if(!user.isPresent()){
           return null;
       }
       User userObj = user.get();
       UserDTO userDTO = modelMapper.map(userObj,UserDTO.class);
       return userDTO;
    }
    @Override
    public User findUserByUsername(String username){
        Optional<User> user = userRepository.findFirstByUsername(username);
        if(!user.isEmpty()){
            return user.get();
        }
        return null;
    }
    @Override
    public User findLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return this.findUserByUsername(username);
    }
    @Override
    public UserDTO updateUser(UserDTO userDTO){
        User user = this.findLoggedUser();
      //  user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        userRepository.save(user);
        return modelMapper.map(user,UserDTO.class);
    }
   @Override
    public boolean isTruePassword(int id,String password){
        UserDTO userDTO = findById(id);
        User user = modelMapper.map(userDTO,User.class);
        boolean check = passwordEncoder.matches(password,user.getPassword());
        return check;
    }
    @Override
    public boolean changePassword(PassChangeDto passChangeDto, String newPassword){
        int id = findLoggedUser().getId();
        String password = passChangeDto.getPassword();

        boolean checkPass = isTruePassword(id,password);
        if(checkPass){
            userRepository.changeUserPassword(passwordEncoder.encode(newPassword));
            return  true;
        }
        return false;
    }

}
