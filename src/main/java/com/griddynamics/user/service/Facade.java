package com.griddynamics.user.service;

import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.dto.ClientDiscountInfoDto;
import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.exception.AddressException;
import com.griddynamics.user.exception.NoSuchElementException;
import com.griddynamics.user.exception.UserException;
import com.griddynamics.user.validator.AddressValidator;
import com.griddynamics.user.validator.UserValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class Facade {
    private final UserService userService;
    private final AddressService addressService;
    private final UserValidator userValidator;
    private final AddressValidator addressValidator;
    private final DiscountInfoService discountInfoService;

    public UserDto saveUser(UserDto userDto) {
        if (!userValidator.isUserDtoValid(userDto)) {
            throw new UserException("User data is not valid");
        }
        if (userService.isEmailInDatabase(userDto.getEmail())) {
            throw new UserException("User with this email already exists");
        }
        return userService.saveUser(userDto);
    }

    public UserDto getUser(Long id) {
        if (!userService.isUserInDatabase(id)) {
            throw new NoSuchElementException("User not found");
        }
        return userService.getUser(id);
    }

    public UserDto getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    public void deleteUser(Long userId) {
        if (!userService.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        userService.deleteUser(userId);
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        if (!userValidator.isUserDtoValid(userDto)) {
            throw new UserException("User data is not valid");
        }
        if (!userService.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        return userService.updateUser(userId, userDto);
    }

    public AddressDto addAddress(Long userId, AddressDto addressDto) {
        if (!addressValidator.validateAddress(addressDto)) {
            throw new AddressException("Address data is not valid");
        }
        if (!userService.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        return addressService.addAddress(userId, addressDto);
    }

    public AddressDto updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        if (!addressValidator.validateAddress(addressDto)) {
            throw new AddressException("Address data is not valid");
        }
        if (!userService.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        return addressService.updateAddress(userId, addressId, addressDto);
    }

    public void deleteAddress(Long userId, Long addressId) {
        if (!userService.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        addressService.deleteAddress(userId, addressId);
    }

    public List<AddressDto> getAddresses(Long userId) {
        if (!userService.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        List<AddressDto> addressesDto = Optional.ofNullable(addressService.getAddresses(userId)).orElse(Collections.emptyList());
        UserDto userDto = userService.getUser(userId);
        addressesDto.forEach(addressDto -> addressDto.setName(userDto.getName()));
        addressesDto.forEach(addressDto -> addressDto.setSurname(userDto.getSurname()));
        return addressesDto;
    }

    public ClientDiscountInfoDto getClientDiscountInfo(Long userId) {
        if (!userService.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        return discountInfoService.getClientDiscountInfo(userId);
    }

    public void setUserVip(Long userId) {
        if (!userService.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        discountInfoService.setUserVip(userId);
    }
}
