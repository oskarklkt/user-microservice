package com.griddynamics.user.service;

import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.dto.ClientDiscountInfoDto;
import com.griddynamics.user.dto.UserDto;

import java.util.List;

public interface Facade {
    UserDto saveUser(UserDto userDto);

    UserDto getUser(Long id);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    void deleteUser(Long userId);

    UserDto updateUser(Long userId, UserDto userDto);

    AddressDto addAddress(Long userId, AddressDto addressDto);

    AddressDto updateAddress(Long userId, Long addressId, AddressDto addressDto);

    void deleteAddress(Long userId, Long addressId);

    List<AddressDto> getAddresses(Long userId);

    ClientDiscountInfoDto getClientDiscountInfo(Long userId);

    void setUserVip(Long userId);
}
