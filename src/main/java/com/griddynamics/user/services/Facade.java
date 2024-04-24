package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.exceptions.AddressException;
import com.griddynamics.user.exceptions.NoSuchElementException;
import com.griddynamics.user.exceptions.UserException;
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

    public UserDto saveUser(UserDto userDto) {
        if (!userValidator.isUserDtoValid(userDto)) {
            throw new UserException("User data is not valid");
        } else if (userService.isEmailInDatabase(userDto.getEmail())) {
            throw new UserException("User with this email already exists");
        }
        return userService.saveUser(userDto);
    }

    public UserDto getUser(Long id) {
        if (!userValidator.isUserInDatabase(id)) {
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

    public String getUserEmail(Long userId) {
        if (!userValidator.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        return userService.getUserEmail(userId);
    }

    public void deleteUser(Long userId) {
        if (!userValidator.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        userService.deleteUser(userId);
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        if (!userValidator.isUserDtoValid(userDto)) {
            throw new UserException("User data is not valid");
        } else if (!userValidator.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        return userService.updateUser(userId, userDto);
    }


    public boolean isEmailInDatabase(String email) {
        return userService.isEmailInDatabase(email);
    }

    public UserDto isUserInDatabase(Long userId) {
        return userService.getUser(userId);
    }

    public AddressDto addAddress(Long userId, AddressDto addressDto) {
        if (!addressValidator.validateAddress(addressDto)) {
            throw new AddressException("Address data is not valid");
        } else if (!userValidator.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        return addressService.addAddress(userId, addressDto);
    }

    public AddressDto updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        if (!addressValidator.validateAddress(addressDto)) {
            throw new AddressException("Address data is not valid");
        } else if (!userValidator.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        return addressService.updateAddress(userId, addressId, addressDto);
    }

    public void deleteAddress(Long userId, Long addressId) {
        if (!userValidator.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        addressService.deleteAddress(userId, addressId);
    }

    public List<AddressDto> getAddresses(Long userId) {
        if (!userValidator.isUserInDatabase(userId)) {
            throw new NoSuchElementException("User not found");
        }
        List<AddressDto> addressesDto = Optional.ofNullable(addressService.getAddresses(userId)).orElse(Collections.emptyList());
        UserDto userDto = userService.getUser(userId);
        addressesDto.forEach(addressDto -> addressDto.setName(userDto.getName()));
        addressesDto.forEach(addressDto -> addressDto.setSurname(userDto.getSurname()));
        return addressesDto;
    }

}
