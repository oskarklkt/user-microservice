package com.griddynamics.user.services;

import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.mappers.UserDtoMapper;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public boolean saveUser(UserDto userDto) {
        User user = userDtoMapper.userDtoToUser(userDto);
        if (userRepository.isEmailInDatabase(user.getEmail())) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public Optional<UserDto> getUser(Long id) {
        return userRepository.getUser(id)
                .map(userDtoMapper::userToUserDto);
    }

    public Optional<UserDto> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email)
                .map(userDtoMapper::userToUserDto);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return users.stream()
                .map(userDtoMapper::userToUserDto)
                .collect(toList());
    }

    public Optional<String> getUserEmail(Long userId) {
        return userRepository.getUser(userId)
                .map(User::getEmail);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    public boolean updateUser(Long userId, UserDto userDto) {
        if (userRepository.isUserInDatabase(userId)) {
            User user = userDtoMapper.userDtoToUser(userDto);
            user.setId(userId);
            userRepository.updateUser(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmailInDatabase(String email) {
        return userRepository.isEmailInDatabase(email);
    }

    public boolean isUserInDatabase(Long userId) {
        return userRepository.isUserInDatabase(userId);
    }
}