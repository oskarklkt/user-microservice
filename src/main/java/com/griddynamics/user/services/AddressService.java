package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.mappers.AddressDtoMapper;
import com.griddynamics.user.models.Address;
import com.griddynamics.user.repositories.AddressRepository;
import com.griddynamics.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoMapper addressDtoMapper;
    private final UserRepository userRepository;

    public boolean addAddress(Long userId, AddressDto addressDto) {
        Address address = addressDtoMapper.addressDtoToAddress(addressDto);
        if (userRepository.isUserInDatabase(userId)) {
            addressRepository.save(userId, address);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        if (!userRepository.isUserInDatabase(userId) || !addressRepository.isAddressInDatabase(userId, addressId)) {
            return false;
        }
        Address address = addressDtoMapper.addressDtoToAddress(addressDto);
        addressRepository.deleteAddress(userId, addressId);
        addressRepository.save(userId, address);
        return true;
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
        return addresses.stream()
                .map(address -> userRepository.getUser(address.getUserId())
                        .map(user -> addressDtoMapper.addressToAddressDto(address, Optional.of(user)))
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}