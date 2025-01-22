package com.user.micro.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.micro.demo.bean.User

import java.util.List;

@Service
public class UserRepository {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository;
    }
}
