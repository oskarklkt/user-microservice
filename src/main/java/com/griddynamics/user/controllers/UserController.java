package com.griddynamics.user.controllers;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.services.Facade;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class UserController {
    private final Facade facade;

    public boolean saveUser(UserDto userDto) {
        return facade.saveUser(userDto);
    }

    public Optional<UserDto> getUser(Long id) {
        return facade.getUser(id);
    }

    public Optional<UserDto> getUserByEmail(String email) {
        return facade.getUserByEmail(email);
    }

    public List<UserDto> getAllUsers() {
        return facade.getAllUsers();
    }

    public Optional<String> getUserEmail(Long userId) {
        return facade.getUserEmail(userId);
    }

    public boolean deleteUser(Long userId) {
        return facade.deleteUser(userId);
    }

    public boolean updateUser(Long userId, UserDto userDto) {
        return facade.updateUser(userId, userDto);
    }


    public boolean isEmailInDatabase(String email) {
        return facade.isEmailInDatabase(email);
    }

    public boolean isUserInDatabase(Long userId) {
        return facade.isUserInDatabase(userId);
    }

    public boolean addAddress(Long userId, AddressDto addressDto) {
        return facade.addAddress(userId, addressDto);
    }

    public boolean updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        return facade.updateAddress(userId, addressId, addressDto);
    }

    public boolean deleteAddress(Long userId, Long addressId) {
        return facade.deleteAddress(userId, addressId);
    }

    public Optional<List<AddressDto>> getAddresses(Long userId) {
        return facade.getAddresses(userId);
    }
}
