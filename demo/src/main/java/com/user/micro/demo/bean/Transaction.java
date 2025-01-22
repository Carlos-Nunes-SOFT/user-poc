package com.user.micro.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.micro.demo.enums.TransactionType;
import jakarta.persistence.*;

@Entity(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JsonIgnore
    private User user;

    protected  Transaction(){}

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

