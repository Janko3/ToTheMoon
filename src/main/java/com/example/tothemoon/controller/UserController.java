package com.example.tothemoon.controller;

import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.*;
import com.example.tothemoon.security.TokenUtils;
import com.example.tothemoon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    UserService userService;

    UserDetailsService userDetailsService;

    AuthenticationManager authenticationManager;

    TokenUtils tokenUtils;

    @Autowired
    public UserController(UserService userService,UserDetailsService userDetailsService,
                          AuthenticationManager authenticationManager,
                          TokenUtils tokenUtils){
        this.userService= userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
    }

    @PostMapping()
    public ResponseEntity<RegisterDTO>create(@RequestBody @Validated RegisterDTO newUser){
        User createdUser = userService.createUser(newUser);

        if (createdUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

        }
        RegisterDTO registerDTO = new RegisterDTO(createdUser);

        return new ResponseEntity<>(registerDTO,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(userDetails);
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt,expiresIn));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> loadAll() {
        return this.userService.findAll();
    }

    @GetMapping("/whoami")
    public UserDTO loggedUser(Principal user) {
        System.out.println(user);
        return this.userService.findByUsername(user.getName());
    }
    @PutMapping("/edit")
    public ResponseEntity<UserDTO>update(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.updateUser(userDTO),HttpStatus.OK);
    }
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody PassChangeDto passChangeDto,
                                                 @RequestParam String newPassword) {
        boolean passwordChanged = userService.changePassword(passChangeDto, newPassword);
        if (passwordChanged) {
            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Incorrect current password.");
        }
    }

}
