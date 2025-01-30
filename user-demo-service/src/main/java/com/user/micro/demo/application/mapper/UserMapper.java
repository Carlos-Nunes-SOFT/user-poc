package com.user.micro.demo.application.mapper;

import com.user.micro.demo.application.dtos.EncryptedUserDto;
import com.user.micro.demo.application.dtos.UserDto;
import com.user.micro.demo.application.utils.EncodingUtils;
import com.user.micro.demo.domain.user.EncryptedUser;
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

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "balance", source = "balance")
    EncryptedUserDto toEncryptedDto(EncryptedUser encryptedUser);

    default EncryptedUser toEncryptedUser(User user){
        return new EncryptedUser(
                EncodingUtils.encode(user.getId()),
                EncodingUtils.encode(user.getName()),
                EncodingUtils.encode(user.getBalance())
        );
    }
}
