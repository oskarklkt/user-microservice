package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.mappers.AddressDtoMapper;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AddressServiceTest {

    AddressService addressService;
    AddressRepository addressRepository;
    UserRepository userRepository;
    Long userId;
    AddressDto addressDto;
    AddressDtoMapper addressDtoMapper;
    Address address;

    @BeforeEach
    void setUp() {
        addressRepository = Mockito.mock(AddressRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        userId = 1L;
        addressDto = new AddressDto();
        addressService = new AddressService(addressRepository, AddressDtoMapper.INSTANCE, userRepository);
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
        when(addressDtoMapper.addressDtoToAddress(addressDto)).thenReturn(address);
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
        when(addressDtoMapper.addressDtoToAddress(addressDto)).thenReturn(address);
        when(userRepository.isUserInDatabase(userId)).thenReturn(true);

        // When
        AddressDto result = addressService.updateAddress(userId, addressId, addressDto);

        // Then
        verify(addressRepository).deleteAddress(userId, addressId);
        verify(addressRepository).save(any(), any());
    }

    @Test
    void getAddresses_UserHasAddresses_ReturnsAddressListWithDetails() {
        // Given
        List<Address> addresses = Arrays.asList(address);
        User user = Mockito.mock(User.class);
        when(user.getId()).thenReturn(userId);
        when(userRepository.isUserInDatabase(userId)).thenReturn(true);
        when(userRepository.getUser(userId)).thenReturn(Optional.of(user));
        when(addressRepository.findAllByUserId(userId)).thenReturn(addresses);
        when(addressDtoMapper.addressToAddressDto(any(), any())).thenReturn(addressDto);
        when(addressRepository.findAllByUserId(userId)).thenReturn(addresses);
        when(userRepository.getUser(userId)).thenReturn(Optional.of(user));
        when(addressDtoMapper.addressToAddressDto(any(), any())).thenReturn(addressDto);
        AddressRepository.getAddresses().put(1L, addresses);
        // When
        List<AddressDto> results = addressService.getAddresses(userId);

        // Then
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        AddressRepository.getAddresses().clear();
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