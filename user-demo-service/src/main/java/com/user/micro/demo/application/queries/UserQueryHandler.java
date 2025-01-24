package com.user.micro.demo.application.queries;

import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.mapper.TransactionMapper;
import com.user.micro.demo.application.mapper.UserMapper;
import com.user.micro.demo.domain.user.Transaction;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.TransactionRepository;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserQueryHandler {

    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    private UserMapper userMapper;
    private TransactionMapper transactionMapper;

    public UserQueryHandler(
            UserRepository userRepository,
            UserMapper userMapper,
            TransactionRepository transactionRepository,
            TransactionMapper transactionMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
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

}
