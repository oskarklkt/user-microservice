package com.griddynamics.user.mappers;

import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.models.User;

import java.util.function.BiFunction;

public class UserMapper implements BiFunction<Long, UserDto, User> {
    @Override
    public User apply(Long id, UserDto userDto) {
        return User.builder()
                .id(id)
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .gender(userDto.getGender())
                .birthday(userDto.getBirthday())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .profilePhotoUrl(userDto.getProfilePhotoUrl()).build();
    }
}
