package com.griddynamics.user.mappers;

import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.UserRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    @AfterMapping
    default void setUserId(@MappingTarget User user) {
        user.setId(UserRepository.getNextId());
    }
}
