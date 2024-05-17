package com.griddynamics.user.mapper.modelToDto;

import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
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
