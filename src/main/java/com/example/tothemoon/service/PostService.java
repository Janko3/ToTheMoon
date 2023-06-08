package com.example.tothemoon.service;

import com.example.tothemoon.model.Post;
import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.NewPostDTO;

import java.util.List;

public interface PostService {
    Post createPost(NewPostDTO newPostDTO);
    NewPostDTO deletePost(int id);
    NewPostDTO updatePost(NewPostDTO newPostDTO);
    List<NewPostDTO> findAll();
    NewPostDTO findById(int id);


}