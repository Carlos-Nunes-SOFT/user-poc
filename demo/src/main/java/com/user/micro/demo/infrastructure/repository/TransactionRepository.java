package com.user.micro.demo.infrastructure.repository;

import com.user.micro.demo.domain.user.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TransactionRepository extends JpaRepository<Transaction, Long> {
}
