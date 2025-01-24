package com.user.micro.demo.application.controller;

import com.user.micro.demo.application.commands.CreateTransactionCommand;
import com.user.micro.demo.application.commands.CreateUserCommand;
import com.user.micro.demo.application.commands.DeleteUserCommand;
import com.user.micro.demo.application.commands.UserCommandHandler;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.queries.GetUserByIdQuery;
import com.user.micro.demo.application.queries.UserQueryHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserCommandHandler userCommandHandler;
    private UserQueryHandler userQueryHandler;

    public UserController(
            UserCommandHandler userCommandHandler,
            UserQueryHandler userQueryHandler) {
        this.userCommandHandler = userCommandHandler;
        this.userQueryHandler = userQueryHandler;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return this.userQueryHandler.getUsers();
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable Long id){
        GetUserByIdQuery query = new GetUserByIdQuery(id);
        return this.userQueryHandler.getById(query);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserCommand request) {
        UserDto user = this.userCommandHandler.CreateUser(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand((user.getId()))
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        DeleteUserCommand command = new DeleteUserCommand(id);
        this.userCommandHandler.DeleteUser(command);
    }

    @PostMapping("/user/execute-transaction")
    public ResponseEntity<UserDto> executeTransaction(@RequestBody CreateTransactionCommand request){
        UserDto user = this.userCommandHandler.ExecuteTransaction(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(request.userId.toString())
                .buildAndExpand((user.getId()))
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
