package com.user.micro.demo.application.dtos;

public class EncryptedUserDto {

    private String id;
    private String name;
    private String balance;

    public EncryptedUserDto(String id, String name, String balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBalance() {
        return balance;
    }
}
