package com.user.micro.demo.domain.user;

import com.user.micro.demo.domain.user.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Amount should be greater than zero.")
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "user_id") //Creates foreign key to reference user on transaction table
    private User user;

    public Transaction(){}

    public Transaction(Long id, Integer amount, TransactionType type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
}

