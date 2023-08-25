package com.example.tothemoon.service;

import com.example.tothemoon.model.Group;
import com.example.tothemoon.model.dto.GroupDTO;

import java.util.List;
import java.util.Set;

public interface GroupService {
    GroupDTO createGroup(GroupDTO groupDTO);
    GroupDTO deleteGroup(int id);
    GroupDTO updateGroup(GroupDTO groupDTO);

    GroupDTO findById(int id);
    List<GroupDTO> findAll();
    List<GroupDTO>findAllByUser();
}
