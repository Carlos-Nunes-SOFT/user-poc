package com.user.micro.demo.controller;

import com.user.micro.demo.bean.User;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand((savedUser.getId()))
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        Optional<User> user = userService.getById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("No such user with id: " + id);

        userService.deleteUser(id);
    }
}
