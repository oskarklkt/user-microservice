package com.griddynamics.user.controllers;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.services.Facade;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class UserController {
    private final Facade facade;

    public UserDto saveUser(UserDto userDto) {
        return facade.saveUser(userDto);
    }

    public UserDto getUser(Long id) {
        return facade.getUser(id);
    }

    public UserDto getUserByEmail(String email) {
        return facade.getUserByEmail(email);
    }

    public List<UserDto> getAllUsers() {
        return facade.getAllUsers();
    }

    public String getUserEmail(Long userId) {
        return facade.getUserEmail(userId);
    }

    public void deleteUser(Long userId) {
        facade.deleteUser(userId);
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        return facade.updateUser(userId, userDto);
    }


    public boolean isEmailInDatabase(String email) {
        return facade.isEmailInDatabase(email);
    }

    public UserDto isUserInDatabase(Long userId) {
        return facade.isUserInDatabase(userId);
    }

    public AddressDto addAddress(Long userId, AddressDto addressDto) {
        return facade.addAddress(userId, addressDto);
    }

    public AddressDto updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        return facade.updateAddress(userId, addressId, addressDto);
    }

    public void deleteAddress(Long userId, Long addressId) {
        facade.deleteAddress(userId, addressId);
    }

    public List<AddressDto> getAddresses(Long userId) {
        return facade.getAddresses(userId);
    }
}
