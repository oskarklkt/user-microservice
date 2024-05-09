package com.griddynamics.user.controller;

import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.exception.BaseException;
import com.griddynamics.user.service.Facade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class UserController {
    private final Facade facade;

    public UserDto saveUser(UserDto userDto) {
        try {
            return facade.saveUser(userDto);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public UserDto getUser(Long id) {
        try {
            return facade.getUser(id);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public UserDto getUserByEmail(String email) {
        try {
            return facade.getUserByEmail(email);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public List<UserDto> getAllUsers() {
        try {
            return facade.getAllUsers();
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public String getUserEmail(Long userId) {
        try {
            return facade.getUser(userId).getEmail();
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public void deleteUser(Long userId) {
        try {
            facade.deleteUser(userId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        try {
            return facade.updateUser(userId, userDto);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }
}
