package com.example.tothemoon.service.impl;

import com.example.tothemoon.model.Group;
import com.example.tothemoon.model.GroupRequest;
import com.example.tothemoon.model.dto.GroupDTO;
import com.example.tothemoon.model.enums.EUserType;
import com.example.tothemoon.repository.GroupRepository;
import com.example.tothemoon.service.GropuRequestService;
import com.example.tothemoon.service.GroupService;
import com.example.tothemoon.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private UserService userService;
    private ModelMapper modelMapper;
    @Autowired
    @Lazy
    private GropuRequestService gropuRequestService;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,UserService userService,ModelMapper modelMapper){
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;

    }
    @Override
    public GroupDTO createGroup(GroupDTO groupDTO){
        Group group = new Group();
        group.setCreationDate(LocalDateTime.now().toString());
        group.setName(groupDTO.getName());
        group.setDescription(groupDTO.getDescription());
        groupRepository.save(group);
        GroupRequest request = gropuRequestService.createForAdmin(userService.findLoggedUser().getId(),group.getId());
        groupDTO.setId(group.getId());
        return groupDTO;

    }
    @Override
    public GroupDTO deleteGroup(int id){
        Group group = groupRepository.findById(id).orElseThrow(()-> new RuntimeException());
        if (userService.findLoggedUser().getRole() != EUserType.ADMIN){
            throw new RuntimeException("Unauthorized");
        }
        group.setDeleted(true);
        groupRepository.save(group);
        GroupDTO groupDTO =modelMapper.map(group,GroupDTO.class);
        return groupDTO;
    }
    @Override
    public GroupDTO updateGroup(GroupDTO groupDTO){
        Group group = groupRepository.findById(groupDTO.getId()).orElseThrow(()->new RuntimeException());
        if (userService.findLoggedUser().getRole() != EUserType.ADMIN){
            throw new RuntimeException("Unauthorized");
        }
        group.setName(groupDTO.getName());
        group.setDescription(groupDTO.getDescription());
        groupRepository.save(group);
        return modelMapper.map(group,GroupDTO.class);
    }
    @Override
    public GroupDTO findById(int id){
        Group group = groupRepository.findById(id).orElseThrow(()-> new RuntimeException());
        GroupDTO groupDTO = modelMapper.map(group,GroupDTO.class);
        return groupDTO;
    }
    @Override
    public List<GroupDTO>findAll(){
        List<Group>groups = groupRepository.findAllGroups();
        List<GroupDTO>groupDTOS = new ArrayList<>();
        for (Group group: groups){
            GroupDTO groupDTO = modelMapper.map(group,GroupDTO.class);
            groupDTOS.add(groupDTO);
        }
        return groupDTOS;
    }
    @Override
    public List<GroupDTO>findAllByUser(){
        Set<Group> groups = groupRepository.getAllUserGroups(userService.findLoggedUser().getId());
        List<GroupDTO>groupDTOS = new ArrayList<>();
        for (Group group: groups){
            GroupDTO groupDTO = modelMapper.map(group,GroupDTO.class);
            groupDTOS.add(groupDTO);
        }
        return groupDTOS;
    }
}
