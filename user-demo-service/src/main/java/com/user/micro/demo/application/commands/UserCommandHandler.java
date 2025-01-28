package com.user.micro.demo.application.commands;

import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.application.proxy.TransactionServiceProxy;
import com.user.micro.demo.application.utils.UserCommandHandlerUtils;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.domain.user.builder.UserBuilder;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserCommandHandler.class);

    private UserRepository userRepository;
    private UserBuilder userBuilder;
    private UserMapper userMapper;
    private TransactionServiceProxy proxy;
    private UserCommandHandlerUtils utils;

    public UserCommandHandler(
            UserRepository userRepository,
            UserBuilder userBuilder,
            UserMapper userMapper,
            TransactionServiceProxy proxy,
            UserCommandHandlerUtils utils) {
        this.userRepository = userRepository;
        this.userBuilder = userBuilder;
        this.userMapper = userMapper;
        this.proxy = proxy;
        this.utils = utils;
    }

    public UserDto createUser(CreateUserCommand request) {
        //MISSING DUPLICATE CHECK
        User user = this.userBuilder
                .newUser(request.name, request.balance)
                .build();

        this.userRepository.save(user);

        return userMapper.toDto(user);
    }

    public void deleteUser(DeleteUserCommand request){
        User user = this.userRepository.findById(request.id)
                .orElseThrow(() -> new UserNotFoundException("No such user with id: " + request.id));
        this.userRepository.delete(user);
    }

    @Transactional
    public UserDto executeTransaction(Long userId, CreateTransactionCommand request){
        //Orientar mais a um crud; Reduzir complexidade
        //Encriptação e desencriptação

        logger.info("Executing transaction for userId={}, amount={}, type={}",
                userId, request.amount, request.type);

        UserDto userDto = utils.findUserById(userId);

        Long newBalance = this.utils.calculateUserBalance(
                userId, request.amount, request.type);

        logger.info("Calling transaction service to create transaction");
        TransactionDto transaction = proxy.createTransaction(userId, request);
        logger.info("Received transaction response: {}", transaction);

        this.utils.updateUserBalanceWithTransaction(userId, newBalance, transaction);
        userDto.setBalance(newBalance);

        logger.info("Transaction executed successfully and user updated");

        return userDto;
    }
}
