package com.infosys.agentdashboard.controller;

import com.infosys.agentdashboard.entity.UserEntity;
import com.infosys.agentdashboard.models.User;
import com.infosys.agentdashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public HashMap getUser(@PathVariable String id){
        return userService.getSkillsByID(id);
    }

    @PostMapping
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping
    public List<UserEntity> getAll(){
        return userService.getAll();
    }

}
