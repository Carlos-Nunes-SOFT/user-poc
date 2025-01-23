package com.user.micro.demo.application.dtos;

import com.user.micro.demo.domain.user.enums.TransactionType;

public class TransactionDto {
    private Long id;
    private Integer amount;
    private TransactionType type;
}
