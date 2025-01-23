package com.user.micro.demo.application.commands;

import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.domain.user.Transaction;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.domain.user.builder.TransactionBuilder;
import com.user.micro.demo.domain.user.builder.UserBuilder;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.sql.Delete;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandler {

    private UserRepository userRepository;
    private UserBuilder userBuilder;
    private TransactionBuilder transactionBuilder;
    private UserMapper userMapper;

    public UserCommandHandler(
            UserRepository userRepository,
            UserBuilder userBuilder,
            TransactionBuilder transactionBuilder,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userBuilder = userBuilder;
        this.transactionBuilder = transactionBuilder;
        this.userMapper = userMapper;
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
    public UserDto ExecuteTransaction(CreateTransactionCommand request){
        User user = this.userRepository.findById(request.userId)
                .orElseThrow(() -> new UserNotFoundException("No such user with id: " + request.userId));

        Transaction transaction = this.transactionBuilder
                .newTransaction(request.userId, request.amount, request.type)
                .build();

        user.addTransactionToTransactionList(transaction);

        this.userRepository.save(user);

        return this.userMapper.toDto(user);
    }
}
