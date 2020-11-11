package com.infosys.agentdashboard.controller;

import com.infosys.agentdashboard.entity.UserEntity;
import com.infosys.agentdashboard.models.User;
import com.infosys.agentdashboard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/users")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserEntity>  getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public HashMap getUser(@PathVariable String id){
        return userService.getSkillsByID(id);
    }

    @PostMapping
    public List<UserEntity> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public List<UserEntity> deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }

    @DeleteMapping("/{id}/{skill}")
    public List<UserEntity> deleteUser(@PathVariable String id, @PathVariable String skill){
        return userService.deleteSkill(id, skill);
    }
}
