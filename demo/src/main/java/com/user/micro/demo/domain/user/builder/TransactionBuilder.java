package com.user.micro.demo.domain.user.builder;

import com.user.micro.demo.domain.user.enums.TransactionType;
import com.user.micro.demo.domain.user.Transaction;

public interface TransactionBuilder {
    Transaction build();
    TransactionBuilder newTransaction(Long userId, Integer amount, TransactionType type);
}
