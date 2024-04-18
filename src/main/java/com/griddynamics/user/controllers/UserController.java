package com.griddynamics.user.controllers;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.services.Facade;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class UserController {
    private final Facade facade;

    public void saveUser(UserDto userDto) {
        facade.saveUser(userDto);
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

    public void updateUser(Long userId, UserDto userDto) {
        facade.updateUser(userId, userDto);
    }


    public boolean isEmailInDatabase(String email) {
        return facade.isEmailInDatabase(email);
    }

    public boolean isUserInDatabase(Long userId) {
        return facade.isUserInDatabase(userId);
    }

    public void addAddress(Long userId, AddressDto addressDto) {
        facade.addAddress(userId, addressDto);
    }

    public void updateAddress(Long userId, Long addressId,  AddressDto addressDto) {
        facade.updateAddress(userId, addressId, addressDto);
    }

    public void deleteAddress(Long userId, Long addressId) {
        facade.deleteAddress(userId, addressId);
    }

    public List<AddressDto> getAddresses(Long userId) {
        return facade.getAddresses(userId);
    }
}
