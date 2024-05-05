package com.griddynamics.user.controller;

import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.exception.BaseException;
import com.griddynamics.user.service.Facade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class AddressController {
    private final Facade facade;

    public AddressDto addAddress(Long userId, AddressDto addressDto) {
        try {
            return facade.addAddress(userId, addressDto);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public AddressDto updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        try {
            return facade.updateAddress(userId, addressId, addressDto);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public void deleteAddress(Long userId, Long addressId) {
        try {
            facade.deleteAddress(userId, addressId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
    }

    public List<AddressDto> getAddresses(Long userId) {
        try {
            return facade.getAddresses(userId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }
}
