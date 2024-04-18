package com.griddynamics.user.services;

import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.mappers.UserDtoMapper;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public void saveUser(UserDto userDto) {
        User user = userDtoMapper.userDtoToUser(userDto);
        userRepository.save(user);
    }

    public UserDto getUser(Long id) {
        User user = userRepository.getUser(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userDtoMapper.userToUserDto(user);
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return userDtoMapper.INSTANCE.userToUserDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return users.stream()
                .map(userDtoMapper::userToUserDto)
                .collect(toList());
    }

    public String getUserEmail(Long userId) {
        User user = userRepository.getUser(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getEmail();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    public void updateUser(Long userId, UserDto userDto) {
        User user = userDtoMapper.userDtoToUser(userDto);
        user.setId(userId);
        userRepository.updateUser(user);
    }

    public boolean isEmailInDatabase(String email) {
        return userRepository.isEmailInDatabase(email);
    }

    public boolean isUserInDatabase(Long userId) {
        return userRepository.isUserInDatabase(userId);
    }
}
