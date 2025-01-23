package com.user.micro.demo.application.mapper;

import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "balance", source = "balance")
    UserDto toDto(User user);

}
