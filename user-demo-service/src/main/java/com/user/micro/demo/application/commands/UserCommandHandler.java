package com.user.micro.demo.application.commands;

import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.application.proxy.TransactionServiceProxy;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.domain.user.builder.UserBuilder;
import com.user.micro.demo.exception.InsufficientFundsException;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandler {

    private UserRepository userRepository;
    private UserBuilder userBuilder;
    private UserMapper userMapper;
    private TransactionServiceProxy proxy;

    public UserCommandHandler(
            UserRepository userRepository,
            UserBuilder userBuilder,
            UserMapper userMapper,
            TransactionServiceProxy proxy) {
        this.userRepository = userRepository;
        this.userBuilder = userBuilder;
        this.userMapper = userMapper;
        this.proxy = proxy;
    }

    public UserDto CreateUser(CreateUserCommand request) {
        //MISSING DUPLICATE CHECK
        User user = this.userBuilder
                .newUser(request.name, request.balance)
                .build();

        this.userRepository.save(user);

        return userMapper.toDto(user);
    }

    public void DeleteUser(DeleteUserCommand request){
        User user = this.userRepository.findById(request.id)
                .orElseThrow(() -> new UserNotFoundException("No such user with id: " + request.id));
        this.userRepository.delete(user);
    }

    @Transactional
    public UserDto ExecuteTransaction(Long userId, CreateTransactionCommand request){
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No such user with id: " + userId.toString()));

        switch (request.type){
            case "WITHDRAWAL", "TRANSFER" -> {
                if (user.getBalance() < request.amount)
                    throw new InsufficientFundsException("Insufficient funds for this transactions.");
                user.setBalance(user.getBalance() - request.amount);
            }
            case "DEPOSIT" -> user.setBalance(user.getBalance() + request.amount);
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }

        TransactionDto transaction = proxy.createTransaction(userId, request);

        user.addTransactionToTransactionList(transaction);

        user.setBalance(user.getBalance() + request.amount);
        this.userRepository.save(user);

        return this.userMapper.toDto(user);
    }
}
