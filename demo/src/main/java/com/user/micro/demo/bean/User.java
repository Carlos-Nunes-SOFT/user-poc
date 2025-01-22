package com.user.micro.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min=2, message ="Name should have at least 2 characters.")
    private String name;

    private Long balance;

    @OneToMany(mappedBy = "user") //If there's OneToMany without mappedBy,
    // hibernate creates new relationship table user_transaction
    @JsonIgnore
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
