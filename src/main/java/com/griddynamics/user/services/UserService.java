package com.griddynamics.user.services;

import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.mappers.UserDtoMapper;
import com.griddynamics.user.mappers.UserMapper;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserMapper userMapper;

    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.apply(UserRepository.getNextId(), userDto);
        userRepository.save(user);
        return userDto;
    }

    public UserDto getUser(Long id) {
        return userRepository.getUser(id).map(userDtoMapper).orElseThrow(() -> new NoSuchElementException("User not found"));

    }

    public UserDto getUserByEmail(String email) {
        return userRepository.getUserByEmail(email)
                .map(userDtoMapper)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return users.stream()
                .map(userDtoMapper)
                .collect(toList());
    }

    public String getUserEmail(Long userId) {
        return String.valueOf(userRepository.getUser(userId)
                .map(User::getEmail));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.updateUser(userId, userMapper.apply(userId, userDto));
        return userDtoMapper.apply(user);

    }

    public boolean isEmailInDatabase(String email) {
        return userRepository.isEmailInDatabase(email);
    }
}