package com.user.micro.demo.domain.user.strategy;

public class TransactionStrategyFactory {

    public static TransactionStrategy getStrategy(String type){
        return switch(type){
            case "WITHDRAWAL", "TRANSFER" -> new DebitTransactionStrategy();
            case "DEPOSIT" -> new CreditTransactionStrategy();
            default -> throw new IllegalArgumentException("Invalid transaction type");
        };
    }
}
