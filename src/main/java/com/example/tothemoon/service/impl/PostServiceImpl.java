package com.example.tothemoon.service.impl;

import com.example.tothemoon.model.Post;
import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.NewPostDTO;
import com.example.tothemoon.model.dto.UserDTO;
import com.example.tothemoon.repository.PostRepository;
import com.example.tothemoon.service.PostService;
import com.example.tothemoon.service.UserService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserService userService;
    private ModelMapper modelMapper;
    @Autowired
    public PostServiceImpl(PostRepository postRepository,UserService userService,ModelMapper modelMapper){
        this.postRepository= postRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @Override
    public Post createPost(NewPostDTO newPostDTO){
        Post newPost = new Post();
        newPost.setCreationDate(LocalDateTime.now());
        newPost.setContent(newPost.getContent());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserDTO userDTO = userService.findByUsername(username);
        newPost.setUser(modelMapper.map(userDTO,User.class));
        postRepository.save(newPost);
        return newPost;
    }
    @Override
    public List<NewPostDTO> findAll() {
        List<Post> posts = postRepository.findAll();
        List<NewPostDTO> newPostDTOS = new ArrayList<>();

        for (Post post : posts) {
            NewPostDTO newPostDTO = modelMapper.map(post, NewPostDTO.class);
            newPostDTOS.add(newPostDTO);
        }

        return newPostDTOS;
    }

    @Override
    public NewPostDTO findById(int id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent()) {
            return null;
        }

        Post postObject = post.get();
        NewPostDTO newPostDTO = modelMapper.map(postObject, NewPostDTO.class);
        return newPostDTO;
    }

    @Override
    public NewPostDTO deletePost(int id){
        Optional<Post>post = postRepository.findById(id);
        if (!post.isPresent()){
            return null;
        }
        post.get().setDeleted(true);
        postRepository.save(post.get());
        Post postObject = post.get();
        NewPostDTO newPostDTO = modelMapper.map(postObject,NewPostDTO.class);
        return newPostDTO;
    }
    @Override
    public NewPostDTO updatePost(NewPostDTO newPostDTO) {
        Optional<Post> postOptional = postRepository.findById(newPostDTO.getId());
        if (!postOptional.isPresent()) {
            return null;
        }

        Post post = postOptional.get();
        post.setContent(newPostDTO.getContent());
        post.setImages(newPostDTO.getImages());
        postRepository.save(post);

        return modelMapper.map(post, NewPostDTO.class);
    }




}
