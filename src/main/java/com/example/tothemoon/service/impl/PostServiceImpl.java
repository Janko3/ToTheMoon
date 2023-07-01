package com.example.tothemoon.service.impl;

import com.example.tothemoon.model.Image;
import com.example.tothemoon.model.Post;
import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.NewPostDTO;
import com.example.tothemoon.model.dto.UserDTO;
import com.example.tothemoon.model.enums.EUserType;
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
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
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
    public NewPostDTO createPost(NewPostDTO newPostDTO){
        Post newPost = new Post();

        newPost.setCreationDate(LocalDateTime.now());
        newPost.setContent(newPostDTO.getContent());
        User user = userService.findLoggedUser();
        newPost.setUser(user);
        newPost = postRepository.save(newPost);
        newPostDTO.setId(newPost.getId());
        newPostDTO.setUser(modelMapper.map(user,UserDTO.class));
        return newPostDTO;

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
    public List<NewPostDTO> findAllByUser(int id){
        List<Post> posts = postRepository.findAllByUser(id);
        List<NewPostDTO> newPostDTOS = new ArrayList<>();
        for(Post post : posts){
            if (post.getUser().getId() == id) {
                NewPostDTO newPostDTO = modelMapper.map(post, NewPostDTO.class);
                newPostDTO.setUser(modelMapper.map(post.getUser(),UserDTO.class));
                newPostDTOS.add(newPostDTO);
            }
        }
        return newPostDTOS;
    }
    @Override
    public NewPostDTO deletePost(int id)  {

        Post post = postRepository.findFirstByIdWithCollections(id).orElseThrow(()->new RuntimeException());
        System.out.println(userService.findLoggedUser().getId());
        if (userService.findLoggedUser().getRole() != EUserType.ADMIN  || userService.findLoggedUser().getId() != post.getUser().getId()){
            throw new RuntimeException("Unauthorized");
        }
        post.setDeleted(true);
        postRepository.save(post);
        NewPostDTO newPostDTO = modelMapper.map(post,NewPostDTO.class);
        for (Image image:
            post.getImages() ) {
            newPostDTO.getImages().add(image.getPath());
        }
        return newPostDTO;
    }
    @Override
    public NewPostDTO updatePost(NewPostDTO newPostDTO) {
        Post post = postRepository.findFirstByIdWithCollections(newPostDTO.getId()).orElseThrow(()->new RuntimeException());
        if (userService.findLoggedUser().getRole() != EUserType.ADMIN || userService.findLoggedUser().getId() != post.getUser().getId()){
            throw new RuntimeException("Unauthorized");
        }
        post.setContent(newPostDTO.getContent());
        postRepository.save(post);

        return modelMapper.map(post, NewPostDTO.class);
    }







}
