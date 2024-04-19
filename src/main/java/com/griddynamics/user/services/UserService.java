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
        User user = userRepository.getUser(id).orElse(null);
        if (user == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(userDtoMapper.userToUserDto(user));
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
