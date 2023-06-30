package com.example.tothemoon.service;

import com.example.tothemoon.model.Post;
import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.NewPostDTO;
import com.example.tothemoon.model.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface PostService {
    NewPostDTO createPost(NewPostDTO newPostDTO);
    NewPostDTO deletePost(int id);
    NewPostDTO updatePost(NewPostDTO newPostDTO);
    List<NewPostDTO> findAll();
    NewPostDTO findById(int id);
    List<NewPostDTO> findAllByUser(int id);

}
