package com.user.micro.demo.application.dtos;

import com.user.micro.demo.domain.user.enums.TransactionType;

public class TransactionDto {
    private Long userId;
    private Long id;
    private Integer amount;
    private TransactionType type;
    private String timestamp;

    public TransactionDto(Long userId, Long id, Integer amount, TransactionType type, String timestamp) {
        this.userId = userId;
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTimeStamp() {
        return timestamp;
    }
}
