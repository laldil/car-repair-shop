package com.example.car_repair_shop.mapper;

import com.example.car_repair_shop.dto.user.CreateUserRequest;
import com.example.car_repair_shop.dto.user.UserDto;
import com.example.car_repair_shop.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserEntity mapToEntity(CreateUserRequest request);

    UserDto mapToDto(UserEntity entity);
}