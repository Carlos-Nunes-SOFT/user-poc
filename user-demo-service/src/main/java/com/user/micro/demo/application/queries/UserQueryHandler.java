package com.user.micro.demo.application.queries;

import com.user.micro.demo.application.dtos.EncryptedUserDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.EncryptedUserRepository;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserQueryHandler {

    private UserRepository userRepository;
    private EncryptedUserRepository encryptedUserRepository;
    private UserMapper userMapper;

    public UserQueryHandler(
            UserRepository userRepository,
            EncryptedUserRepository encryptedUserRepository,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.encryptedUserRepository = encryptedUserRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getUsers(){
        return this.userRepository.findAll().stream()
                .map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDto getById(GetUserByIdQuery request){
        return this.userRepository.findById(request.id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("No such user for id: " + request.id));
    }

    public List<EncryptedUserDto> getEncryptedUsers(){
        return this.encryptedUserRepository.findAll().stream()
                .map(userMapper::toEncryptedDto).collect(Collectors.toList());
    }

    public EncryptedUserDto getEncryptedById(GetEncryptedUserByIdQuery request){
        return this.encryptedUserRepository.findById(request.id)
                .map(userMapper::toEncryptedDto)
                .orElseThrow(() -> new UserNotFoundException("No such user for id: " + request.id));
    }
}
