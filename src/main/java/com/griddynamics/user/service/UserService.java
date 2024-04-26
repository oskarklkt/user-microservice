package com.griddynamics.user.service;

import com.griddynamics.user.dto.ClientDiscountInfoDto;
import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.mapper.ClientDiscountInfoDtoMapper;
import com.griddynamics.user.mapper.UserDtoMapper;
import com.griddynamics.user.mapper.UserMapper;
import com.griddynamics.user.model.User;
import com.griddynamics.user.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserMapper userMapper;
    private final ClientDiscountInfoDtoMapper ClientDiscountInformationDtoMapper;

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

    public ClientDiscountInfoDto getClientDiscountInfo(Long userId) {
        User user = userRepository.getUser(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        return ClientDiscountInformationDtoMapper.apply(user);
    }

    public void setUserVip(Long userId) {
        userRepository.setUserVip(userId);
    }
}