package com.user.micro.demo.application.dtos;


public class TransactionDto {
    private Long userId;
    private Long id;
    private Integer amount;
    private String type;
    private String timestamp;

    public TransactionDto(Long userId, Long id, Integer amount, String type, String timestamp) {
        this.userId = userId;
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
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

    public String getTimeStamp() {
        return timestamp;
    }
}
