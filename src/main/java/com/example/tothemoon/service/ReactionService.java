package com.example.tothemoon.service;

import com.example.tothemoon.model.dto.ReactionDTO;

import java.util.List;

public interface ReactionService {

    ReactionDTO createReaction(ReactionDTO reactionDTO);
    ReactionDTO deleteReaction(int id);
    ReactionDTO updateReaction(ReactionDTO reactionDTO);
    List<ReactionDTO> findAllFromPost(int postId);
    List<ReactionDTO> findAllFromComment(int commentId);
}
