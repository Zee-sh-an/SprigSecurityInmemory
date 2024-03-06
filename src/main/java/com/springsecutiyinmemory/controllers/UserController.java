package com.springsecutiyinmemory.controllers;

import com.springsecutiyinmemory.models.User;
import com.springsecutiyinmemory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody User user){
        return userService.addUser(user);
    }


}
