package com.griddynamics.user.mapper.dtoToModel;

import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.model.User;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
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
