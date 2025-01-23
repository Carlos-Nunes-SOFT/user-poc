package com.user.micro.demo.domain.user.builder;

import com.user.micro.demo.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserBuilderImpl implements UserBuilder{
    private User user;

    @Override
    public UserBuilder withName(String name){
        this.user.setName(name);
        return this;
    }

    @Override
    public UserBuilder withBalance(Long balance){
        this.user.setBalance(balance);
        return this;
    }

    @Override
    public User build(){
        if(user==null)
            throw new IllegalArgumentException("The user object is not initialized.");
        return user;
    }

    @Override
    public UserBuilder newUser(String name, Long balance){
        user = new User(name, balance);
        return this;
    }
}
