package com.user.micro.demo.domain.user.strategy;

import com.user.micro.demo.exception.InsufficientFundsException;

public class DebitTransactionStrategy implements TransactionStrategy{

    @Override
    public Long calculate(Long currentBalance, Integer amount) {
        if (currentBalance < amount)
            throw new InsufficientFundsException("Insufficient funds for this transaction.");
        return currentBalance - amount;
    }
}
