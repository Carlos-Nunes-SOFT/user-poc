package com.user.micro.demo.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private List<Transaction> transactions;

    protected User() {}

    public User(Long id, String name, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", transactions=" + transactions +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String name) {
        this.name = name;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
