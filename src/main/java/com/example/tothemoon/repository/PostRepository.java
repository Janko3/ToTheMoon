package com.example.tothemoon.repository;

import com.example.tothemoon.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
