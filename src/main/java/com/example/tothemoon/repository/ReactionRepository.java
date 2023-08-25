package com.example.tothemoon.repository;

import com.example.tothemoon.model.Reaction;
import com.example.tothemoon.model.dto.ReactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Integer> {

    @Query("SELECT r FROM Reaction r WHERE r.comment.id = ?1 and r.isDeleted = false ")
    List<Reaction> findAllFromComment(int commentId);

    @Query("SELECT r FROM Reaction r WHERE r.post.id =?1 and r.isDeleted = false ")
    List<Reaction> findAllFromPost(int postId);
}
