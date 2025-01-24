package com.user.micro.demo.application.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateTransactionCommand {

    public Integer amount;

    public String type;
}
