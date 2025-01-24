package com.user.micro.demo.domain.user.builder;

import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.domain.user.User;

public interface UserBuilder {
    User build();
    UserBuilder newUser(String name, Long balance);
    UserBuilder addTransaction(TransactionDto transaction);
}
