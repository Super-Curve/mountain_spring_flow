package com.mountain.web.application.mapper;

import com.mountain.web.application.dto.UserCreateDto;
import com.mountain.web.application.dto.UserDto;
import com.mountain.web.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    User toEntity(UserCreateDto dto);
    
    UserDto toDto(User user);
}