package com.griddynamics.user.services;

import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.enums.Gender;
import com.griddynamics.user.mappers.UserDtoMapper;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {
    UserRepository userRepository;
    UserService userService;
    UserDto userDto;
    User user;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository, UserDtoMapper.INSTANCE);
        userDto = new UserDto("test33", "Tester", Gender.MALE, "01.01.2000", "+123456789", "test@gmail.com", "url");
        user = new User(1L, "test33", "Tester", Gender.MALE, "01.01.2000", "+123456789", "test@gmail.com", "url");
    }

    @Test
    void saveUser() {
        userService.saveUser(userDto);
        verify(userRepository, Mockito.times(1)).save(any());
    }

    @Test
    void getUser() {
        //when
        when(userRepository.getUser(1L)).thenReturn(Optional.of(user));
        userService.getUser(1L);
        //then
        verify(userRepository, Mockito.times(1)).getUser(any());
    }

    @Test
    void getUserByEmail() {
        //when
        when(userRepository.getUserByEmail("test")).thenReturn(Optional.of(user));
        userService.getUserByEmail("test");
        //then
        verify(userRepository, Mockito.times(1)).getUserByEmail(any());
    }

    @Test
    void getAllUsers() {
        userService.getAllUsers();
        verify(userRepository, Mockito.times(1)).getAllUsers();
    }

    @Test
    void getUserEmail() {
        //when
        when(userRepository.getUser(1L)).thenReturn(Optional.of(user));
        userService.getUserEmail(1L);
        //then
        verify(userRepository, Mockito.times(1)).getUser(any());
    }

    @Test
    void deleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, Mockito.times(1)).deleteUser(any());
    }



    @Test
    void isEmailInDatabase() {
        //when
        when(userRepository.isEmailInDatabase("test")).thenReturn(true);
        userService.isEmailInDatabase("test");
        //then
        verify(userRepository, Mockito.times(1)).isEmailInDatabase(any());
    }

    @Test
    void isUserInDatabase() {
        //when
        when(userRepository.isUserInDatabase(1L)).thenReturn(true);
        userService.isUserInDatabase(1L);
        //then
        verify(userRepository, Mockito.times(1)).isUserInDatabase(any());
    }
}