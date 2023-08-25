package com.example.tothemoon.controller;

import com.example.tothemoon.model.dto.GroupDTO;
import com.example.tothemoon.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/groups")
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }
    @PostMapping
    public ResponseEntity<GroupDTO>create(@RequestBody GroupDTO groupDTO){
        return new ResponseEntity<>(this.groupService.createGroup(groupDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDTO>delete(@PathVariable int id){
        return new ResponseEntity<>(this.groupService.deleteGroup(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO>update(@RequestBody GroupDTO groupDTO,@PathVariable int id){
        groupDTO.setId(id);
        return new ResponseEntity<>(this.groupService.updateGroup(groupDTO),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO>findOne(@PathVariable int id){
        return new ResponseEntity<>(this.groupService.findById(id),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<GroupDTO>> findAll(){
        return new ResponseEntity<>(this.groupService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/userGroups/all")
    public ResponseEntity<List<GroupDTO>>findAllByUser(){
        return new ResponseEntity<>(this.groupService.findAllByUser(),HttpStatus.OK);
    }
}
