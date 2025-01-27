package com.user.micro.demo.application.dtos;


import java.time.LocalDateTime;

public class TransactionDto {
    private Long userId;
    private String id;
    private Integer amount;
    private String type;
    private LocalDateTime timestamp;

    public TransactionDto(Long userId, String id, Integer amount, String type, LocalDateTime timestamp) {
        this.userId = userId;
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}
