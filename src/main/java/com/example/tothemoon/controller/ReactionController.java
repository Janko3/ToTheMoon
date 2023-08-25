package com.example.tothemoon.controller;

import com.example.tothemoon.model.dto.ReactionDTO;
import com.example.tothemoon.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reactions")
public class ReactionController {
    private ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService){
        this.reactionService = reactionService;
    }
    @PostMapping
    public ResponseEntity<ReactionDTO> create(@RequestBody ReactionDTO reactionDTO){
        return new ResponseEntity<>(this.reactionService.createReaction(reactionDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ReactionDTO> delete(@PathVariable int id){
        return new ResponseEntity<>(this.reactionService.deleteReaction(id),HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<List<ReactionDTO>>findAllByPost(@PathVariable int id){
        return new ResponseEntity<>(this.reactionService.findAllFromPost(id),HttpStatus.OK);
    }
    @GetMapping("/comments/{id}")
    public ResponseEntity<List<ReactionDTO>>findAllComment(@PathVariable int id){
        return new ResponseEntity<>(this.reactionService.findAllFromComment(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReactionDTO>update(@RequestBody ReactionDTO reactionDTO){
       return new  ResponseEntity<>(this.reactionService.updateReaction(reactionDTO),HttpStatus.OK);
    }
}

