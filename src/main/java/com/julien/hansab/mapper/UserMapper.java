package com.julien.hansab.mapper;

import com.julien.hansab.domain.User;
import com.julien.hansab.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDto(User user);
}