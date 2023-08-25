package com.example.tothemoon.controller;

import com.example.tothemoon.model.dto.CommentDTO;
import com.example.tothemoon.model.dto.CreateCommentDto;
import com.example.tothemoon.service.CommentService;
import com.example.tothemoon.service.PostService;
import com.example.tothemoon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts/{id}/comments")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;

    }
    @PostMapping
    public ResponseEntity<CommentDTO>create(@RequestBody CreateCommentDto createCommentDto, @PathVariable("id") String postId,@RequestParam(value = "owner", required = false) String owner){
        return new ResponseEntity<>(this.commentService.createComment(createCommentDto,Integer.parseInt(postId),(owner!= null) ? owner:null), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO>findOne(@PathVariable int id){
        return new ResponseEntity<>(this.commentService.findById(id),HttpStatus.OK);
    }
    @GetMapping("/{owner}")
    public ResponseEntity<Object> getCommentsAndCountWithOwners(
            @PathVariable int postId, @PathVariable int[] ownerIds) {

        List<CommentDTO> commentsAndCount = commentService.findCommentsAndCountWithOwner(postId, ownerIds);
        return ResponseEntity.ok(commentsAndCount);
    }
    @GetMapping
    public ResponseEntity<List<CommentDTO>>findComments(@PathVariable("id") String postId){
        return new ResponseEntity<>(this.commentService.findAllCommentsWithoutOwnerByPostId(Integer.parseInt(postId)),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO>update(@RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(this.commentService.updateComment(commentDTO),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDTO>delete(@PathVariable int id){
        return new ResponseEntity<>(this.commentService.deleteComment(id),HttpStatus.OK);
    }
}
