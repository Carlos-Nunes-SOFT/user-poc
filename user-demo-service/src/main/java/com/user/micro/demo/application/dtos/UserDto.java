package com.user.micro.demo.application.dtos;

public class UserDto {
    private Long id;
    private String name;
    private Long balance;

    public UserDto(Long id, String name, Long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBalance() {
        return balance;
    }
}
