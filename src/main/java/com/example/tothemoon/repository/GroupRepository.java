package com.example.tothemoon.repository;

import com.example.tothemoon.model.Group;
import com.example.tothemoon.model.dto.GroupDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Component
@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    @Query("SELECT g FROM Group g WHERE g.isDeleted = FALSE")
     List<Group> findAllGroups();
    @Query("SELECT g FROM GroupRequest gr JOIN gr.group g WHERE gr.approved = true AND gr.user.id = :userId")
    Set<Group> getAllUserGroups(Integer userId);

}
