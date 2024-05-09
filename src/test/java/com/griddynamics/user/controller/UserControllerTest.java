package com.griddynamics.user.controller;


import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.service.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private Facade facade;

    @InjectMocks
    private UserController userController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_ShouldInvokeFacadeSaveUser() {
        //given
        UserDto userDto = new UserDto();
        //when
        userController.saveUser(userDto);
        //then
        verify(facade).saveUser(userDto);
    }

    @Test
    void getUser() {
        //when
        userController.getUser(anyLong());
        //then
        verify(facade).getUser(anyLong());
    }

    @Test
    void getUserByEmail() {
        //when
        userController.getUserByEmail(anyString());
        //then
        verify(facade).getUserByEmail(anyString());
    }

    @Test
    void getAllUsers() {
        //when
        userController.getAllUsers();
        //then
        verify(facade).getAllUsers();
    }


    @Test
    void deleteUser() {
        //when
        userController.deleteUser(anyLong());
        //then
        verify(facade).deleteUser(anyLong());
    }

    @Test
    void updateUser() {
        //given
        UserDto userDto = new UserDto();
        //when
        userController.updateUser(1L, userDto);
        //then
        verify(facade).updateUser(1L, userDto);
    }
}