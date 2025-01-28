package com.user.micro.demo.application.utils;

import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.exception.InsufficientFundsException;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCommandHandlerUtils {

    private static final Logger logger = LoggerFactory.getLogger(UserCommandHandlerUtils.class);

    private UserRepository userRepository;
    private  UserMapper userMapper;

    public UserCommandHandlerUtils(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
            case "DEPOSIT" -> userBalance+= amount;
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }

        return userBalance;
    }

    public UserDto findUserById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No such user with id: " + userId.toString()));

        return userMapper.toDto(user);
    }

    public void updateUserBalanceWithTransaction(Long userId, Long newBalance,TransactionDto transactionDto){
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No such user with id: " + userId.toString()));

        user.setBalance(newBalance);
        user.addTransactionToTransactionList(transactionDto);
        userRepository.save(user);
        logger.info("Updated user balance for userId={} to {}", userId, newBalance);
    }
}
