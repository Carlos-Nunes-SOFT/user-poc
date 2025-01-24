package com.user.micro.demo.application.mapper;

import com.user.micro.demo.application.dtos.TransactionDto;
import com.user.micro.demo.domain.user.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now().toString())")
    TransactionDto toDto(Transaction transaction);

}
