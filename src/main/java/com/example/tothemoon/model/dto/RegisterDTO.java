package com.example.tothemoon.model.dto;

import com.example.tothemoon.model.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDTO {
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;

    public RegisterDTO(User createdUser){
        this.username = createdUser.getUsername();
        this.email = createdUser.getEmail();
        this.firstName = createdUser.getFirstName();
        this.lastName = createdUser.getLastName();

    }

}
