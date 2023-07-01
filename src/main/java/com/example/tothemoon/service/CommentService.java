package com.example.tothemoon.service;

import com.example.tothemoon.model.Comment;
import com.example.tothemoon.model.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO);
    CommentDTO deleteComment(int id);
    CommentDTO updateComment(CommentDTO commentDTO);
    CommentDTO findById(int id);
    List<CommentDTO> findAllCommentsWithoutOwnerByPostId(int id);
    List<CommentDTO> findCommentsAndCountWithOwner();


}
