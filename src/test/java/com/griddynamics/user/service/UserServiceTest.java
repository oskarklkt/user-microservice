package com.griddynamics.user.service;

import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.enumeration.ClientType;
import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.mapper.modelToDto.UserDtoMapper;
import com.griddynamics.user.mapper.dtoToModel.UserMapper;
import com.griddynamics.user.model.User;
import com.griddynamics.user.repository.UserRepository;
import com.griddynamics.user.service.implementations.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {
    UserRepository userRepository;
    UserServiceImpl userService;
    UserDto userDto;
    User user;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository, new UserDtoMapper(), new UserMapper());
        userDto = new UserDto("test33", "Tester", Gender.MALE, "01.01.2000", "+123456789", "test@gmail.com", "url");
        user = new User(1L, "test33", "Tester", Gender.MALE, "01.01.2000", "+123456789", "test@gmail.com", "url", "01.01.2021", ClientType.BASIC);
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


}