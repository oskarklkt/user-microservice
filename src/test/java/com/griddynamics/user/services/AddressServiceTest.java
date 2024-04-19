package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.mappers.AddressDtoMapper;
import com.griddynamics.user.repositories.AddressRepository;
import com.griddynamics.user.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class AddressServiceTest {

    AddressService addressService;
    AddressRepository addressRepository;
    UserRepository userRepository;
    Long userId;
    AddressDto addressDto;

    @BeforeEach
    void setUp() {
        addressRepository = Mockito.mock(AddressRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        userId = 1L;
        addressDto = new AddressDto();
        addressService = new AddressService(addressRepository, AddressDtoMapper.INSTANCE, userRepository);
    }

    @Test
    void addAddressNonExisting() {
        // when
        addressService.addAddress(userId, addressDto);
        // then
        assertFalse(addressService.addAddress(userId, addressDto));
    }

    @Test
    void addAddress() {
        // when
        when(userRepository.isUserInDatabase(userId)).thenReturn(true);
        addressService.addAddress(userId, addressDto);
        // then
        assertTrue(addressService.addAddress(userId, addressDto));
    }

    @Test
    void updateAddress() {
        // when
        when(userRepository.isUserInDatabase(userId)).thenReturn(true);
        when(addressRepository.isAddressInDatabase(userId, 1L)).thenReturn(true);
        addressService.updateAddress(userId, 1L, addressDto);
        // then
        assertTrue(addressService.updateAddress(userId, 1L, addressDto));
    }

    @Test
    void updateNonExistingAddress() {
        // when
        addressService.updateAddress(userId, 1L, addressDto);
        // then
        assertFalse(addressService.updateAddress(userId, 1L, addressDto));
    }

    @Test
    void deleteAddress() {
        // when
        when(userRepository.isUserInDatabase(userId)).thenReturn(true);
        // then
        assertTrue(addressService.deleteAddress(userId, 1L));
    }

    @Test
    void deleteNonExistingAddress() {
        // when
        when(userRepository.isUserInDatabase(userId)).thenReturn(false);
        // then
        assertFalse(addressService.deleteAddress(userId, 1L));
    }

}