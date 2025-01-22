package com.user.micro.demo.repository;

import com.user.micro.demo.bean.Transaction;
import com.user.micro.demo.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TransactionRepository extends JpaRepository<Transaction, Long> {
}
