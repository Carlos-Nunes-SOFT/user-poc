package com.user.micro.demo.service;

import com.user.micro.demo.bean.Transaction;
import com.user.micro.demo.enums.TransactionType;
import com.user.micro.demo.exception.UserNotFoundException;
import com.user.micro.demo.repository.TransactionRepository;
import com.user.micro.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.micro.demo.bean.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<User> getUsers(){

        return userRepository.findAll();
    }

    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public Transaction executeTransaction(Long userId, TransactionType type, Integer amount){
        Optional<User> tempUser = userRepository.findById(userId);
        if(tempUser.isEmpty())
            throw new UserNotFoundException("No such user with id: " + userId);

        User user = tempUser.get();

        if(type == TransactionType.WITHDRAWAL || type == TransactionType.TRANSFER) {
            if (user.getBalance() < amount)
                throw new IllegalArgumentException("Insufficient funds for this transaction, please try to deposit first.");
            user.setBalance(user.getBalance() - amount);
        }

        if(type == TransactionType.DEPOSIT)
            user.setBalance(user.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(type);
        transaction.setAmount(amount);

        userRepository.save(user);

        return transactionRepository.save(transaction);
    }
}
