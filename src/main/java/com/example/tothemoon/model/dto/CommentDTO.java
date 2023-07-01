package com.example.tothemoon.model.dto;

import com.example.tothemoon.model.Comment;
import com.example.tothemoon.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Integer id;
    private String text;
    private Comment owner;
    private String timestamp;
    private UserDTO user;
    private NewPostDTO post;

}
