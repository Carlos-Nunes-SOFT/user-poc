package com.user.micro.demo.application.controller;

import com.user.micro.demo.domain.user.Transaction;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.domain.user.enums.TransactionType;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.TransactionRepository;
import com.user.micro.demo.application.service.UserService;
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
    private TransactionRepository transactionRepository;

    public UserController(UserService userService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.transactionRepository = transactionRepository;
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

    @GetMapping("/user/{id}/transactions")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long id){
        Optional<User> user = userService.getById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("No such user with id: " + id);

        return user.get().getTransactions();
    }

    @PostMapping("/user/{id}/execute-transaction")
    //Also possible to create a DTO for the TransactionRequest which allows the creation of JSON type body instead of route ?type=..&amount=..
    public ResponseEntity<Transaction> executeTransaction(@PathVariable Long id, @RequestParam TransactionType type, @RequestParam Integer amount){
        Transaction transaction = userService.executeTransaction(id, type, amount);

        return ResponseEntity.ok(transaction);
    }
}
