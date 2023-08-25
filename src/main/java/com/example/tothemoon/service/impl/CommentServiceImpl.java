package com.example.tothemoon.service.impl;

import com.example.tothemoon.model.Comment;
import com.example.tothemoon.model.Post;
import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.CommentDTO;
import com.example.tothemoon.model.dto.CreateCommentDto;
import com.example.tothemoon.model.dto.NewPostDTO;
import com.example.tothemoon.model.dto.UserDTO;
import com.example.tothemoon.model.enums.EUserType;
import com.example.tothemoon.repository.CommentRepository;
import com.example.tothemoon.repository.UserRepository;
import com.example.tothemoon.service.CommentService;
import com.example.tothemoon.service.PostService;
import com.example.tothemoon.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private ModelMapper modelMapper;
    private PostService postService;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService,ModelMapper modelMapper,PostService postService){
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.postService = postService;
    }
    @Override
    public CommentDTO createComment(CreateCommentDto createCommentDto, int postId,String ownerId){
        CommentDTO commentDTO = new CommentDTO();
        Comment newComment = new Comment();
        newComment.setText(createCommentDto.getText());
        newComment.setTimestamp(LocalDate.now());
        User user = (userService.findLoggedUser());
        newComment.setUser(user);
        if (ownerId != null){
            newComment.setOwner(commentRepository.getById(Integer.parseInt(ownerId)));
        }
        NewPostDTO newPostDTO = postService.findById(postId);
        Post post = modelMapper.map(newPostDTO,Post.class);
        newComment.setPost(post);
        newComment = commentRepository.save(newComment);
        commentDTO.setUser(modelMapper.map(user, UserDTO.class));
        commentDTO.setId(newComment.getId());
        commentDTO.setText(newComment.getText());
        commentDTO.setTimestamp(newComment.getTimestamp().toString());
        commentDTO.setOwnerId(ownerId!= null ? Integer.parseInt(ownerId) : null);
        return commentDTO;

    }
    @Override
    public CommentDTO findById(int id){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new RuntimeException());;

        Comment commentObj = comment;
        CommentDTO commentDTO = modelMapper.map(commentObj,CommentDTO.class);
        return commentDTO;
    }
    @Override
    public List<CommentDTO> findAllCommentsWithoutOwnerByPostId(int id){
        List<Comment> comments = commentRepository.findAllCommentsWithoutOwnerPostId(id);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment comment : comments){
            CommentDTO commentDTO = modelMapper.map(comment,CommentDTO.class);
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }
    @Override
    public List<CommentDTO> findCommentsAndCountWithOwner(int postId, int[] ownerIds) {
        List<Comment> comments = commentRepository.findCommentsAndCountWithOwner(postId, ownerIds);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment comment : comments){
            CommentDTO commentDTO = modelMapper.map(comment,CommentDTO.class);
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }

    @Override
    public CommentDTO deleteComment(int id){
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new RuntimeException("Comment not found"));
        if(comment.getUser().getRole() != EUserType.ADMIN && userService.findLoggedUser().getId() != comment.getUser().getId() && comment.getPost().getUser().getId() != userService.findLoggedUser().getId()){
            throw new RuntimeException("Unauthorized");
        }
        comment.setDeleted(true);
        commentRepository.save(comment);
        CommentDTO commentDTO = modelMapper.map(comment,CommentDTO.class);
        return commentDTO;
    }
    @Override
    public CommentDTO updateComment(CommentDTO commentDTO){
        Comment comment = commentRepository.findById(commentDTO.getId()).orElseThrow(()-> new RuntimeException("Comment not found"));
        if(comment.getUser().getRole() != EUserType.ADMIN && userService.findLoggedUser().getId() != comment.getUser().getId() && comment.getPost().getUser().getId() != userService.findLoggedUser().getId()){
            throw new RuntimeException("Unauthorized");
        }
        comment.setText(commentDTO.getText());
        comment.setTimestamp(LocalDate.now());
        commentRepository.save(comment);
        return modelMapper.map(comment,CommentDTO.class);
    }


}
