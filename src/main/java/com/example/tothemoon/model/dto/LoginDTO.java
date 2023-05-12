package com.example.tothemoon.model.dto;

import com.example.tothemoon.model.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    private int id;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public LoginDTO(User createdUser){
        this.id = createdUser.getId();
        this.email = createdUser.getEmail();
    }
}
