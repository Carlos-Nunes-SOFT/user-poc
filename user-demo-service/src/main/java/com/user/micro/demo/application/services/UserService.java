package com.user.micro.demo.application.services;

import com.user.micro.demo.application.commands.CreateUserCommand;
import com.user.micro.demo.application.commands.DeleteUserCommand;
import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.application.utils.EncodingUtils;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.domain.user.builder.UserBuilder;
import com.user.micro.demo.domain.user.strategy.TransactionStrategy;
import com.user.micro.demo.domain.user.strategy.TransactionStrategyFactory;
import com.user.micro.demo.exception.InsufficientFundsException;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.EncryptedUserRepository;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final EncryptedUserRepository encryptedUserRepository;
    private final UserMapper userMapper;
    private final UserBuilder userBuilder;

    public UserService(UserRepository userRepository,
                       EncryptedUserRepository encryptedUserRepository,
                       UserMapper userMapper,
                       UserBuilder userBuilder) {
        this.userRepository = userRepository;
        this.encryptedUserRepository = encryptedUserRepository;
        this.userMapper = userMapper;
        this.userBuilder = userBuilder;
    }

    @Transactional
    public UserDto createUser(CreateUserCommand request) {
        User user = this.userBuilder
                .newUser(request.name, request.balance)
                .build();

        this.userRepository.save(user);

        //Persist data to encrypted table
        this.encryptedUserRepository.save(userMapper.toEncryptedUser(user));

        return userMapper.toDto(user);
    }

    @Transactional
    public void deleteUser(DeleteUserCommand request){
        User user = this.userRepository.findById(request.id)
                .orElseThrow(() -> new UserNotFoundException("No such user with id: " + request.id));
        this.userRepository.delete(user);
        this.encryptedUserRepository.deleteById(EncodingUtils.encode(user.getId()));
    }

    public Long calculateUserBalance(Long userId, Integer amount, String type) {
        logger.info("Updating balance for userId={}, transaction type={}, amount={}",
                userId, type, amount);

        Long userBalance = this.userRepository.findBalanceById(userId).getBalance();
        TransactionStrategy strategy = TransactionStrategyFactory.getStrategy(type);

        return strategy.calculate(userBalance, amount);
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

        this.userRepository.save(user);
        this.encryptedUserRepository.save(userMapper.toEncryptedUser(user));
        logger.info("Updated user balance for userId={} to {}", userId, newBalance);
    }


}
