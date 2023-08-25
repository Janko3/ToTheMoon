package com.example.tothemoon.model.dto;

import com.example.tothemoon.model.Comment;
import com.example.tothemoon.model.Post;
import com.example.tothemoon.model.User;
import com.example.tothemoon.model.enums.EReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReactionDTO {
    private Integer id;
    private EReactionType reactionType;
    private UserDTO user;
    private Integer commentId;
    private Integer postId;
    private String timestamp;
}
