package com.user.micro.demo.application.services;

import com.user.micro.demo.application.commands.CreateTransactionCommand;
import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.proxy.TransactionServiceProxy;
import com.user.micro.demo.application.utils.EncodingUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    private final TransactionServiceProxy transactionProxy;

    public TransactionService(UserService userService, TransactionServiceProxy transactionProxy) {
        this.userService = userService;
        this.transactionProxy = transactionProxy;
    }

    @Transactional
    public UserDto executeTransaction(Long userId, CreateTransactionCommand request){

        logger.info("Executing transaction for userId={}, amount={}, type={}",
                userId, request.amount, request.type);

        UserDto userDto = this.userService.findUserById(userId);

        Long newBalance = this.userService.calculateUserBalance(
                userId, request.amount, request.type);

        logger.info("Calling transaction service to create transaction");
        String encodedUserId = EncodingUtils.encode(userId);
        TransactionDto transaction = transactionProxy.createTransaction(encodedUserId, request);
        logger.info("Received transaction response: {}", transaction);

        this.userService.updateUserBalanceWithTransaction(userId, newBalance, transaction);
        userDto.setBalance(newBalance);

        logger.info("Transaction executed successfully and user updated");

        return userDto;
    }
}
