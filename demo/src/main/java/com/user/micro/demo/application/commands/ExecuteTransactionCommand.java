package com.user.micro.demo.application.commands;

import com.user.micro.demo.domain.user.enums.TransactionType;

public class ExecuteTransactionCommand {
    private Long userId;
    private Integer amount;
    private TransactionType type;
}
