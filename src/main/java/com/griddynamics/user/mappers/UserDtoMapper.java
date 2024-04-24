package com.griddynamics.user.mappers;

import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.models.User;

import java.util.function.Function;

public class UserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .gender(user.getGender())
                .birthday(user.getBirthday())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .profilePhotoUrl(user.getProfilePhotoUrl()).build();
    }
}
