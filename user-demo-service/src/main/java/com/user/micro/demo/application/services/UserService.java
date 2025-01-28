package com.user.micro.demo.application.services;

import com.user.micro.demo.application.commands.CreateUserCommand;
import com.user.micro.demo.application.commands.DeleteUserCommand;
import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.domain.user.builder.UserBuilder;
import com.user.micro.demo.exception.InsufficientFundsException;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserBuilder userBuilder;

    public UserService(UserRepository userRepository, UserMapper userMapper, UserBuilder userBuilder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userBuilder = userBuilder;
    }

    public UserDto createUser(CreateUserCommand request) {
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

    public Long calculateUserBalance(Long userId, Integer amount, String type) {
        logger.info("Updating balance for userId={}, transaction type={}, amount={}",
                userId, type, amount);

        Long userBalance = this.userRepository.findBalanceById(userId);

        switch (type) {
            case "WITHDRAWAL", "TRANSFER" -> {
                if (userBalance < amount)
                    throw new InsufficientFundsException("Insufficient funds for this transactions.");
                userBalance -= amount;
            }
            case "DEPOSIT" -> userBalance += amount;
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }

        return userBalance;
    }

    public UserDto findUserById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No such user with id: " + userId.toString()));

        return userMapper.toDto(user);
    }

    @Transactional
    public void updateUserBalanceWithTransaction(Long userId, Long newBalance, TransactionDto transactionDto){
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No such user with id: " + userId.toString()));

        user.setBalance(newBalance);
        user.addTransactionToTransactionList(transactionDto);
        userRepository.save(user);
        logger.info("Updated user balance for userId={} to {}", userId, newBalance);
    }
}
