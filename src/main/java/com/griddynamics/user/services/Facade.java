package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Facade {
    private final UserService userService;
    private final AddressService addressService;

    public void saveUser(UserDto userDto) {
        userService.saveUser(userDto);
    }

    public UserDto getUser(Long id) {
        return userService.getUser(id);
    }

    public UserDto getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    public String getUserEmail(Long userId) {
        return userService.getUserEmail(userId);
    }

    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }

    public void updateUser(Long userId, UserDto userDto) {
        userService.updateUser(userId, userDto);
    }


    public boolean isEmailInDatabase(String email) {
        return userService.isEmailInDatabase(email);
    }

    public boolean isUserInDatabase(Long userId) {
        return userService.isUserInDatabase(userId);
    }

    public void addAddress(Long userId, AddressDto addressDto) {
        addressService.addAddress(userId, addressDto);
    }

    public void updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        addressService.updateAddress(userId, addressId, addressDto);
    }

    public void deleteAddress(Long userId, Long addressId) {
        addressService.deleteAddress(userId, addressId);
    }

    public List<AddressDto> getAddresses(Long userId) {
        return addressService.getAddresses(userId);
    }
}
