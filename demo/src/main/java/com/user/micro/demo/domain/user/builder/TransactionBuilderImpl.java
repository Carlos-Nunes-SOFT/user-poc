package com.user.micro.demo.domain.user.builder;

import com.user.micro.demo.domain.user.Transaction;
import com.user.micro.demo.domain.user.enums.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class TransactionBuilderImpl implements TransactionBuilder{
    private Transaction transaction;

    @Override
    public Transaction build() {
        if(transaction==null)
            throw new IllegalArgumentException("The transaction object is not initialized.");
        return transaction;
    }

    @Override
    public TransactionBuilder newTransaction(Long userId, Integer amount, TransactionType type) {
        transaction = new Transaction(userId, amount, type);
        return this;
    }
}
