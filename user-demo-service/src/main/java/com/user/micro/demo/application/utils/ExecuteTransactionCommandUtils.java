package com.user.micro.demo.application.utils;

import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.exception.InsufficientFundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteTransactionCommandUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteTransactionCommandUtils.class);

    public static UserDto updateUserBalance(UserDto user, Integer amount, String type) {
        logger.info("Updating balance for userId={}, transaction type={}, amount={}",
                user.getId(), type, amount);

        Long updatedBalance = user.getBalance();

        switch (type) {
            case "WITHDRAWAL", "TRANSFER" -> {
                if (user.getBalance() < amount)
                    throw new InsufficientFundsException("Insufficient funds for this transactions.");
                updatedBalance -= amount;
            }
            case "DEPOSIT" -> updatedBalance+= amount;
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }

        logger.info("Updated user balance for userId={} to {}", user.getId(), updatedBalance);

        return new UserDto(user.getId(), user.getName(), updatedBalance);

    }
}
