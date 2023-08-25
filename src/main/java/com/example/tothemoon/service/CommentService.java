package com.example.tothemoon.service;

import com.example.tothemoon.model.Comment;
import com.example.tothemoon.model.dto.CommentDTO;
import com.example.tothemoon.model.dto.CreateCommentDto;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(CreateCommentDto createCommentDto, int postId,String ownerId);
    CommentDTO deleteComment(int id);
    CommentDTO updateComment(CommentDTO commentDTO);
    CommentDTO findById(int id);
    List<CommentDTO> findAllCommentsWithoutOwnerByPostId(int id);
    List<CommentDTO> findCommentsAndCountWithOwner(int postId,int commentId[]);


}
