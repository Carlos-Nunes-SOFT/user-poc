package com.user.micro.demo.controller;

import com.user.micro.demo.bean.Transaction;
import com.user.micro.demo.bean.User;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.repository.TransactionRepository;
import com.user.micro.demo.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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

    @PostMapping("/user/{id}/transaction")
    public ResponseEntity<Transaction> createPostForUser(@PathVariable Long id, @Valid @RequestBody Transaction transaction)
    {
        Optional<User> user = userService.getById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("No such user with id: " + id);

        transaction.setUser(user.get());
        Transaction savedTransaction = transactionRepository.save(transaction);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand((savedTransaction.getId()))
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/user/{id}/transactions")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long id){
        Optional<User> user = userService.getById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("No such user with id: " + id);

        return user.get().getTransactions();
    }

}
