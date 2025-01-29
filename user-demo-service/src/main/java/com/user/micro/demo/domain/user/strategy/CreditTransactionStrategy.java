package com.user.micro.demo.domain.user.strategy;

public class CreditTransactionStrategy implements TransactionStrategy{

    @Override
    public Long calculate(Long currentBalance, Integer amount) {
        return currentBalance + amount;
    }
}
