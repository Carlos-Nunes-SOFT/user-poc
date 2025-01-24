package com.user.micro.demo.service;

import com.user.micro.demo.application.commands.CreateTransactionCommand;
import com.user.micro.demo.application.commands.UserCommandHandler;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.domain.user.Transaction;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.domain.user.enums.TransactionType;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.infrastructure.repository.TransactionRepository;
import com.user.micro.demo.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @InjectMocks
//    private UserCommandHandler userService;
//
//    private CreateTransactionCommand request = new CreateTransactionCommand();
//
//    private User user;
//
//    @BeforeEach
//    void setUp(){
//        user = new User();
//        user.setId(1L);
//        user.setBalance(1000L);
//    }
//
//    @Test
//    void testExecuteTransaction_type_deposit(){
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction());
//
//        UserDto transaction = userService.ExecuteTransaction(request);
//
//        assertEquals(1500L, user.getBalance());
//        verify(userRepository).save(user);
//    }
//
//    @Test
//    void testExecuteTransaction_type_deposit_user_not_found(){
//        when(userRepository.findById(2L)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(UserNotFoundException.class, () ->{
//            userService.executeTransaction(2L, TransactionType.DEPOSIT, 500);
//        });
//
//        assertEquals("No such user with id: 2", exception.getMessage());
//    }
//
//    @Test
//    void testExecuteTransaction_type_withdrawal_with_funds(){
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction());
//
//        Transaction transaction = userService.executeTransaction(1L, TransactionType.WITHDRAWAL, 500);
//
//        assertEquals(500L, user.getBalance());
//        verify(userRepository).save(user);
//    }
//
//    @Test
//    void testExecuteTransaction_type_withdrawal_without_funds(){
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
//            userService.executeTransaction(1L, TransactionType.WITHDRAWAL, 1500);
//        });
//
//        assertEquals("Insufficient funds for this transaction, please try to deposit first.", exception.getMessage());
//    }
//
//    //Repeat for TRANSFER type

}
