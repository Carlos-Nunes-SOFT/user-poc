package com.user.micro.demo.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.micro.demo.application.dtos.TransactionDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "encrypted_user_table")
public class EncryptedUser {

    @Id
    private String id;

    private String name;

    private String balance;

    public EncryptedUser() {}

    public EncryptedUser(String id, String name, String balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBalance() {
        return balance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
