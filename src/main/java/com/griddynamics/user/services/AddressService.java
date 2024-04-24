package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.exceptions.BaseException;
import com.griddynamics.user.mappers.AddressDtoMapper;
import com.griddynamics.user.mappers.AddressMapper;
import com.griddynamics.user.models.Address;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.AddressRepository;
import com.griddynamics.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoMapper addressDtoMapper;
    private final AddressMapper addressMapper;
    private final UserRepository userRepository;

    public AddressDto addAddress(Long userId, AddressDto addressDto) {
        Address address = addressMapper.apply(AddressRepository.getNextAddressId(), userId, addressDto);
        addressRepository.save(userId, address);
        return addressDto;
    }

    public AddressDto updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        addressRepository.deleteAddress(userId, addressId);
        addressRepository.save(userId, addressMapper.apply(AddressRepository.getNextAddressId(), userId, addressDto));
        return addressDto;
    }

    public boolean deleteAddress(Long userId, Long addressId) {
        if (!userRepository.isUserInDatabase(userId)) {
            return false;
        } else {
            addressRepository.deleteAddress(userId, addressId);
            return true;
        }
    }

    public List<AddressDto> getAddresses(Long userId) {
        List<Address> addresses = addressRepository.findAllByUserId(userId);
        if (addresses == null) {
            return Collections.emptyList();
        }
        User user = userRepository.getUser(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        return addresses.stream()
                .map(address -> addressDtoMapper.apply(user, address))
                .collect(Collectors.toList());


    }
}