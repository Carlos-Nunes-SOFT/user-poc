package com.user.micro.demo.application.commands;

import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.domain.user.builder.UserBuilder;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandler {

    private UserRepository userRepository;
    private UserBuilder userBuilder;
    private UserMapper userMapper;

    public UserCommandHandler(
            UserRepository userRepository,
            UserBuilder userBuilder,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userBuilder = userBuilder;
        this.userMapper = userMapper;
    }

    public UserDto CreateUser(CreateUserCommand request) {
        //MISSING DUPLICATE CHECK
        User user = this.userBuilder
                .newUser(request.name, request.balance)
                .build();

        this.userRepository.save(user);

        return userMapper.toDto(user);
    }
}
