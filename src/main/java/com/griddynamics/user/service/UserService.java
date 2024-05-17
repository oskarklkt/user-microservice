package com.griddynamics.user.service;

import com.griddynamics.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    UserDto getUser(Long id);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    String getUserEmail(Long userId);

    void deleteUser(Long userId);

    UserDto updateUser(Long userId, UserDto userDto);

    boolean isEmailInDatabase(String email);

    boolean isUserInDatabase(Long userId);
}
