package com.example.tothemoon.controller;

import com.example.tothemoon.model.Post;
import com.example.tothemoon.model.dto.NewPostDTO;
import com.example.tothemoon.model.dto.UserDTO;
import com.example.tothemoon.service.PostService;
import com.example.tothemoon.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService,UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<NewPostDTO>create(@RequestBody NewPostDTO newPostDTO){
        return new ResponseEntity<>(this.postService.createPost(newPostDTO), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<NewPostDTO>> findAll(){
        return new ResponseEntity<>(this.postService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NewPostDTO>findOne(@PathVariable int id){
        return new ResponseEntity<>(this.postService.findById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<NewPostDTO> update(@RequestBody NewPostDTO newPostDTO) {
        return new ResponseEntity<>(this.postService.updatePost(newPostDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewPostDTO> delete(@PathVariable int id) {
        return new ResponseEntity<>(this.postService.deletePost(id), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NewPostDTO>> findAllPostsByUser(@PathVariable Integer userId) {

        List<NewPostDTO> postsByUser = postService.findAllByUser(userId);

        if (postsByUser.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(postsByUser);
    }
}
