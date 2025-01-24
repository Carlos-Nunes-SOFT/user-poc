package com.user.micro.demo.application.commands;

import com.user.micro.demo.domain.user.enums.TransactionType;

public class CreateTransactionCommand {
    public Long userId;
    public Integer amount;
    public TransactionType type;
}
