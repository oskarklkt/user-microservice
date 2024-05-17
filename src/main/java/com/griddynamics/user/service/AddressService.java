package com.griddynamics.user.service;

import com.griddynamics.user.dto.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto addAddress(Long userId, AddressDto addressDto);

    AddressDto updateAddress(Long userId, Long addressId, AddressDto addressDto);

    boolean deleteAddress(Long userId, Long addressId);

    List<AddressDto> getAddresses(Long userId);
}
