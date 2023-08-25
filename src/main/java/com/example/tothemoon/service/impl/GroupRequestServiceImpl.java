package com.example.tothemoon.service.impl;

import com.example.tothemoon.model.Group;
import com.example.tothemoon.model.GroupRequest;
import com.example.tothemoon.model.User;
import com.example.tothemoon.model.dto.GroupDTO;
import com.example.tothemoon.model.dto.UserDTO;
import com.example.tothemoon.repository.GroupRequestRepository;
import com.example.tothemoon.service.GropuRequestService;
import com.example.tothemoon.service.GroupService;
import com.example.tothemoon.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GroupRequestServiceImpl implements GropuRequestService {
    private GroupService groupService;
    private UserService userService;
    private ModelMapper modelMapper;
    private GroupRequestRepository groupRequestRepository;
    @Autowired
    public GroupRequestServiceImpl(GroupService groupService,UserService userService,ModelMapper modelMapper,GroupRequestRepository groupRequestRepository){
        this.groupService = groupService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.groupRequestRepository = groupRequestRepository;
    }
    @Override
    public GroupRequest createForAdmin(Integer userId, Integer groupId) {
        GroupDTO groupDTO = groupService.findById(groupId);
        Group group = modelMapper.map(groupDTO,Group.class);
        UserDTO userDTO = userService.findById(userId);
        User user = modelMapper.map(userDTO,User.class);
        GroupRequest req  = new GroupRequest();
        req.setCreatedAt(LocalDateTime.now());
        req.setApproved(true);
        req.setAt(LocalDateTime.now());
        req.setGroup(group);
        req.setUser(user);

        return groupRequestRepository.save(req);
    }
}
