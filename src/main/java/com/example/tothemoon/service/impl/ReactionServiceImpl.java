package com.example.tothemoon.service.impl;

import com.example.tothemoon.model.Comment;
import com.example.tothemoon.model.Post;
import com.example.tothemoon.model.Reaction;
import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.CommentDTO;
import com.example.tothemoon.model.dto.NewPostDTO;
import com.example.tothemoon.model.dto.ReactionDTO;
import com.example.tothemoon.model.dto.UserDTO;
import com.example.tothemoon.model.enums.EUserType;
import com.example.tothemoon.repository.ReactionRepository;
import com.example.tothemoon.service.CommentService;
import com.example.tothemoon.service.PostService;
import com.example.tothemoon.service.ReactionService;
import com.example.tothemoon.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {
    private ReactionRepository reactionRepository;
    private PostService postService;
    private CommentService commentService;
    private UserService userService;
    private ModelMapper modelMapper;
    @Autowired
    public ReactionServiceImpl(ReactionRepository reactionRepository, PostService postService, CommentService commentService, UserService userService,ModelMapper modelMapper){
        this.reactionRepository = reactionRepository;
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @Override
    public ReactionDTO createReaction(ReactionDTO reactionDTO){
        Reaction reaction = new Reaction();
        reaction.setTimestamp(LocalDate.now());
        reaction.setReactionType(reactionDTO.getReactionType());
        User user = (userService.findLoggedUser());
        reaction.setUser(user);
        if(reactionDTO.getPostId() != null){
            NewPostDTO postDTO = postService.findById(reactionDTO.getPostId());
            Post post = modelMapper.map(postDTO,Post.class);
            reaction.setPost(post);

        }
        if(reactionDTO.getCommentId() != null){
            CommentDTO commentDTO = commentService.findById(reactionDTO.getCommentId());
            Comment comment = modelMapper.map(commentDTO,Comment.class);
            reaction.setComment(comment);
        }
        reaction = reactionRepository.save(reaction);
        reactionDTO.setUser(modelMapper.map(user, UserDTO.class));
        reactionDTO.setId(reaction.getId());
        reactionDTO.setTimestamp(reaction.getTimestamp().toString());
        reactionDTO.setReactionType(reaction.getReactionType());
        return reactionDTO;

    }

    @Override
    public ReactionDTO deleteReaction(int id){
        Reaction reaction = reactionRepository.findById(id).orElseThrow(()-> new RuntimeException());
        if(reaction.getUser().getRole() != EUserType.ADMIN && userService.findLoggedUser().getId() != reaction.getUser().getId()){
            throw new RuntimeException("Unauthorized");
        }

        reactionRepository.delete(reaction);
        ReactionDTO reactionDTO = modelMapper.map(reaction,ReactionDTO.class);
        return  reactionDTO;
    }
    @Override
    public ReactionDTO updateReaction(ReactionDTO reactionDTO){
        Reaction reaction = reactionRepository.findById(reactionDTO.getId()).orElseThrow(()-> new RuntimeException());
        if (userService.findLoggedUser().getRole() != EUserType.ADMIN && userService.findLoggedUser().getId() != reaction.getUser().getId()){
            throw new RuntimeException("Unauthorized");
        }
        reaction.setReactionType(reactionDTO.getReactionType());
        reaction.setTimestamp(LocalDate.now());
        reactionRepository.save(reaction);
        return modelMapper.map(reaction,ReactionDTO.class);
    }
    @Override
    public List<ReactionDTO> findAllFromPost(int postId){
        List<Reaction> reactions = reactionRepository.findAllFromPost(postId);
        List<ReactionDTO> reactionDTOS = new ArrayList<>();
        for (Reaction reaction: reactions){
            if (reaction.getPost().getId() == postId){
                ReactionDTO reactionDTO = modelMapper.map(reaction,ReactionDTO.class);
                reactionDTOS.add(reactionDTO);
            }
        }
        return reactionDTOS;
    }
    @Override
    public List<ReactionDTO> findAllFromComment(int commentId){
        List<Reaction> reactions = reactionRepository.findAllFromComment(commentId);
        List<ReactionDTO> reactionDTOS = new ArrayList<>();
        for (Reaction reaction: reactions){
            if (reaction.getComment().getId() == commentId){
                ReactionDTO reactionDTO = modelMapper.map(reaction,ReactionDTO.class);
                reactionDTOS.add(reactionDTO);
            }

        }
        return reactionDTOS;
    }
}
