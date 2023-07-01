package com.example.tothemoon.model.dto;

import com.example.tothemoon.model.Comment;
import com.example.tothemoon.model.Image;
import com.example.tothemoon.model.Reaction;
import com.example.tothemoon.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPostDTO {
    private Integer id;
    private UserDTO user;
    private String content;
    private Set<String> images = new HashSet<>();
    private String creationDate;



}
