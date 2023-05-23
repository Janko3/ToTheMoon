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
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPostDTO {
    private Integer id;
    private String content;
    private Set<Image> images;
    private LocalDateTime creationDate;
    private Set<MultipartFile> files;


}
