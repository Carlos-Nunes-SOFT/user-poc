package com.user.micro.demo.application.proxy;

import com.user.micro.demo.application.commands.CreateTransactionCommand;
import com.user.micro.demo.application.dtos.TransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "transaction-demo-service", url ="localhost:8081")
public interface TransactionServiceProxy {

    @GetMapping("/transaction/transactions")
    public List<TransactionDto> getTransactionsByUserId(@RequestParam(name = "userId") Long userId);

    @PostMapping("/transaction/execute-transaction")
    public TransactionDto createTransaction(@RequestParam(name= "userId") Long userId, @RequestBody CreateTransactionCommand request);
}
