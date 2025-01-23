package com.user.micro.demo.domain.user.builder;

import com.user.micro.demo.domain.user.Transaction;
import com.user.micro.demo.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserBuilderImpl implements UserBuilder{
    private User user;

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

    @Override
    public UserBuilder addTransaction(Transaction transaction) {
        user.addTransactionToTransactionList(transaction);
        return this;
    }
}
