package com.example.tothemoon.repository;

import com.example.tothemoon.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
}
