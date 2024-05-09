package com.griddynamics.user.service;

import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.exception.AddressException;
import com.griddynamics.user.exception.UserException;
import com.griddynamics.user.validator.AddressValidator;
import com.griddynamics.user.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FacadeTest {

    @Mock
    UserService userService;
    @Mock
    AddressService addressService;
    @Mock
    UserDto userDto;
    @Mock
    AddressDto addressDto;
    Facade facade;
    @Mock
    UserValidator userValidator;
    @Mock
    AddressValidator addressValidator;
    @Mock
    DiscountInfoService discountInfoService;

    @BeforeEach
    void setUp() {
        facade = new Facade(userService, addressService, userValidator, addressValidator, discountInfoService);
    }


    @Test
    void updateUser_ValidUser_ReturnsUpdatedUser() {
        // Setup
        Long userId = 1L;
        when(userService.isUserInDatabase(userId)).thenReturn(true);
        when(userValidator.isUserDtoValid(userDto)).thenReturn(true);
        when(userService.updateUser(userId, userDto)).thenReturn(userDto);

        // Execute
        UserDto result = facade.updateUser(userId, userDto);

        // Verify
        assertEquals(userDto, result);
        verify(userService).updateUser(userId, userDto);
    }

    @Test
    void updateUser_InvalidUser_ThrowsException() {
        // Setup
        Long userId = 1L;
        when(userValidator.isUserDtoValid(any())).thenReturn(true);
        when(userService.isUserInDatabase(userId)).thenReturn(false);

        // Execute & Verify
        assertThrows(com.griddynamics.user.exception.NoSuchElementException.class, () -> facade.updateUser(userId, userDto));
    }

    @Test
    void deleteUser_UserExists_DeletesUser() {
        // Setup
        Long userId = 1L;
        when(userService.isUserInDatabase(userId)).thenReturn(true);

        // Execute
        facade.deleteUser(userId);

        // Verify
        verify(userService).deleteUser(userId);
    }

    @Test
    void deleteUser_UserNotFound_ThrowsException() {
        // Setup
        Long userId = 1L;
        when(userService.isUserInDatabase(userId)).thenReturn(false);

        // Execute & Verify
        assertThrows(com.griddynamics.user.exception.NoSuchElementException.class, () -> facade.deleteUser(userId));
    }

    @Test
    void addAddress_ValidParameters_ReturnsAddedAddress() {
        // Setup
        Long userId = 1L;
        when(userService.isUserInDatabase(userId)).thenReturn(true);
        when(addressValidator.validateAddress(addressDto)).thenReturn(true);
        when(addressService.addAddress(userId, addressDto)).thenReturn(addressDto);

        // Execute
        AddressDto result = facade.addAddress(userId, addressDto);

        // Verify
        assertEquals(addressDto, result);
        verify(addressService).addAddress(userId, addressDto);
    }

    @Test
    void addAddress_InvalidAddress_ThrowsException() {
        // Setup
        Long userId = 1L;
        when(addressValidator.validateAddress(addressDto)).thenReturn(false);

        // Execute & Verify
        assertThrows(AddressException.class, () -> facade.addAddress(userId, addressDto));
    }

    @Test
    void updateAddress_ValidParameters_ReturnsUpdatedAddress() {
        // Setup
        Long userId = 1L;
        Long addressId = 2L;
        when(userService.isUserInDatabase(userId)).thenReturn(true);
        when(addressValidator.validateAddress(addressDto)).thenReturn(true);
        when(addressService.updateAddress(userId, addressId, addressDto)).thenReturn(addressDto);

        // Execute
        AddressDto result = facade.updateAddress(userId, addressId, addressDto);

        // Verify
        assertEquals(addressDto, result);
        verify(addressService).updateAddress(userId, addressId, addressDto);
    }

    @Test
    void deleteAddress_ValidParameters_DeletesAddress() {
        // Setup
        Long userId = 1L;
        Long addressId = 2L;
        when(userService.isUserInDatabase(userId)).thenReturn(true);

        // Execute
        facade.deleteAddress(userId, addressId);

        // Verify
        verify(addressService).deleteAddress(userId, addressId);
    }

    @Test
    void getUser() {
        //when
        when(userService.isUserInDatabase(1L)).thenReturn(true);
        facade.getUser(1L);
        //then
        Mockito.verify(userService).getUser(1L);
    }

    @Test
    void getUserByEmail() {
        //when
        facade.getUserByEmail("email");
        //then
        Mockito.verify(userService).getUserByEmail("email");
    }

    @Test
    void getAllUsers() {
        //when
        facade.getAllUsers();
        //then
        Mockito.verify(userService).getAllUsers();
    }


    @Test
    void saveUser_WhenUserDtoIsInvalid_ThrowsUserException() {
        UserDto userDto = new UserDto();
        when(userValidator.isUserDtoValid(userDto)).thenReturn(false);

        UserException thrown = assertThrows(UserException.class, () -> facade.saveUser(userDto),
                "User data is not valid");

        assertEquals("User data is not valid", thrown.getMessage());
        verify(userService, never()).saveUser(userDto); // Ensure no attempt is made to save
    }

    @Test
    void saveUser_WhenEmailExists_ThrowsUserException() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        when(userValidator.isUserDtoValid(userDto)).thenReturn(true);
        when(userService.isEmailInDatabase(userDto.getEmail())).thenReturn(true);

        UserException thrown = assertThrows(UserException.class, () -> facade.saveUser(userDto),
                "User with this email already exists");

        assertEquals("User with this email already exists", thrown.getMessage());
        verify(userService, never()).saveUser(userDto); // Ensure no attempt is made to save
    }

    @Test
    void saveUser_WhenUserDtoIsValidAndEmailIsNew_SavesUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("newuser@example.com");
        when(userValidator.isUserDtoValid(userDto)).thenReturn(true);
        when(userService.isEmailInDatabase(userDto.getEmail())).thenReturn(false);
        when(userService.saveUser(userDto)).thenReturn(userDto);

        UserDto savedUser = facade.saveUser(userDto);

        assertEquals(userDto, savedUser);
        verify(userService).saveUser(userDto);
    }

    @Test
    void getAddresses_UserNotFound_ThrowsNoSuchElementException() {
        Long userId = 1L;
        when(userService.isUserInDatabase(userId)).thenReturn(false);

        assertThrows(com.griddynamics.user.exception.NoSuchElementException.class, () -> facade.getAddresses(userId),
                "User not found");
        verify(addressService, never()).getAddresses(any());
    }

    @Test
    void getAddresses_UserHasNoAddresses_ReturnsEmptyList() {
        Long userId = 1L;
        UserDto userDto = new UserDto("John", "Doe", Gender.MALE, "2001-04-25", "500204248",  "john.doe@example.com", "http://example.com");
        when(userService.isUserInDatabase(userId)).thenReturn(true);
        when(addressService.getAddresses(userId)).thenReturn(null);
        when(userService.getUser(userId)).thenReturn(userDto);

        List<AddressDto> result = facade.getAddresses(userId);

        assertTrue(result.isEmpty());
        verify(addressService).getAddresses(userId);
    }

    @Test
    void getAddresses_UserHasAddresses_ReturnsAddressListWithDetails() {
        Long userId = 1L;
        AddressDto addressDto = new AddressDto();
        List<AddressDto> addressList = List.of(addressDto);
        UserDto userDto = new UserDto("John", "Doe", Gender.MALE, "2001-04-25", "500204248",  "john.doe@example.com", "http://example.com");
        when(userService.isUserInDatabase(userId)).thenReturn(true);
        when(addressService.getAddresses(userId)).thenReturn(addressList);
        when(userService.getUser(userId)).thenReturn(userDto);

        List<AddressDto> result = facade.getAddresses(userId);

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Doe", result.get(0).getSurname());
        verify(addressService).getAddresses(userId);
        verify(userService).getUser(userId);
    }

}
