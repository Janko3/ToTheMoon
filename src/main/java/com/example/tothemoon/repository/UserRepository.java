package com.example.tothemoon.repository;

import com.example.tothemoon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findFirstByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User set password = :password")
    void changeUserPassword( String password);

}
