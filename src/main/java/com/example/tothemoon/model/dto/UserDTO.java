package com.example.tothemoon.model.dto;

import com.example.tothemoon.model.enums.EUserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private EUserType role;
    private Integer id;
}
