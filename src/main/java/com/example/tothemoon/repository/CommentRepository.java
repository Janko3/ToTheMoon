package com.example.tothemoon.repository;

import com.example.tothemoon.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query("select c from Comment c where c.owner is null and c.post.id= ?1")
    List<Comment> findAllCommentsWithoutOwnerPostId(int id);

    @Query("SELECT c.owner.id, COUNT(c) FROM Comment c WHERE c.owner in ?2 and c.post.id = ?1 GROUP BY c.owner.id")
    List<Comment> findCommentsAndCountWithOwner(int postId,int[] ownerId);





}
