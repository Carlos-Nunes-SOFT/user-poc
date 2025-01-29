package com.user.micro.demo.domain.user.strategy;

public interface TransactionStrategy {
    Long calculate(Long currentBalance, Integer amount);
}
