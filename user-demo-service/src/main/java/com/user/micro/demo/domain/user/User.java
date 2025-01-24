package com.user.micro.demo.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message ="Name should have at least 2 characters.")
    private String name;

    private Long balance;

    //@OneToMany //If there's OneToMany without mappedBy,
    // hibernate creates new relationship table user_transaction
    @JsonIgnore
    private transient List<Transaction> transactions = new ArrayList<>();

    public User() {}

    public User(String name, Long balance) {
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransactionToTransactionList(Transaction transaction){
        if(transaction==null)
            throw new IllegalArgumentException("Transaction cannot be null.");
        this.transactions.add(transaction);
    }
}
