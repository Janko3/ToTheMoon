package com.example.tothemoon.repository;

import com.example.tothemoon.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
