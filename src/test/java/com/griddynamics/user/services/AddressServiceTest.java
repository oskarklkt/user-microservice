package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.enums.Gender;
import com.griddynamics.user.mappers.AddressDtoMapper;
import com.griddynamics.user.mappers.AddressMapper;
import com.griddynamics.user.models.Address;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.AddressRepository;
import com.griddynamics.user.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AddressServiceTest {

    AddressService addressService;
    AddressRepository addressRepository;
    UserRepository userRepository;
    Long userId;
    AddressDto addressDto;
    AddressDtoMapper addressDtoMapper;
    AddressMapper addressMapper;
    Address address;

    @BeforeEach
    void setUp() {
        addressRepository = Mockito.mock(AddressRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        userId = 1L;
        addressDto = new AddressDto();
        addressMapper = Mockito.mock(AddressMapper.class);
        addressDtoMapper = Mockito.mock(AddressDtoMapper.class);
        addressService = new AddressService(addressRepository, addressDtoMapper, addressMapper, userRepository);
        addressDtoMapper = Mockito.mock(AddressDtoMapper.class);
        address = new Address(1L, 1L, "Poland", "Warsaw", "00-001", "Marszałkowska", "1", "1", "1");
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

    @Test
    void addAddress_Success_ReturnsAddressDto() {
        // Given
        when(addressMapper.apply(AddressRepository.getNextAddressId(), userId, addressDto)).thenReturn(address);
        when(userRepository.isUserInDatabase(userId)).thenReturn(true);

        // When
        AddressDto result = addressService.addAddress(userId, addressDto);

        // Then
        verify(addressRepository).save(any(), any());
    }

    @Test
    void updateAddress_Success_ReturnsUpdatedAddressDto() {
        // Given
        Long addressId = 2L;
        when(addressMapper.apply(addressId, userId, addressDto)).thenReturn(address);
        when(userRepository.isUserInDatabase(userId)).thenReturn(true);

        // When
        AddressDto result = addressService.updateAddress(userId, addressId, addressDto);

        // Then
        verify(addressRepository).deleteAddress(userId, addressId);
        verify(addressRepository).save(any(), any());
    }


    @Test
    void getAddresses_UserHasNoAddresses_ReturnsEmptyList() {
        // Given
        when(addressRepository.findAllByUserId(userId)).thenReturn(Collections.emptyList());

        // When
        List<AddressDto> results = addressService.getAddresses(userId);

        // Then
        assertTrue(results.isEmpty());
    }

}