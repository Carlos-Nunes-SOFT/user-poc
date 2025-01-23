package com.user.micro.demo.domain.user.builder;

import com.user.micro.demo.domain.user.User;

public interface UserBuilder {
    UserBuilder withName(String name);
    UserBuilder withBalance(Long balance);
    User build();
    UserBuilder newUser(String name, Long balance);
}
