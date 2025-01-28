package com.user.micro.demo.application.commands;

import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.application.proxy.TransactionServiceProxy;
import com.user.micro.demo.application.services.TransactionService;
import com.user.micro.demo.application.services.UserService;
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

    private UserService userService;
    private TransactionService transactionService;

    public UserCommandHandler(
            UserService userService,
            TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    public UserDto createUser(CreateUserCommand request) {
        return this.userService.createUser(request);
    }

    public void deleteUser(DeleteUserCommand request){
        this.userService.deleteUser(request);
    }

    @Transactional
    public UserDto executeTransaction(Long userId, CreateTransactionCommand request){
        return this.transactionService.executeTransaction(userId, request);
    }
}
