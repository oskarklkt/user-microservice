package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.mappers.AddressDtoMapper;
import com.griddynamics.user.models.Address;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.AddressRepository;
import com.griddynamics.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

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
        if (addressRepository.findAllByUserId(userId) == null) {
            return null;
        }
        return addressRepository.findAllByUserId(userId)
                .stream()
                .map(address -> {
                    Optional<User> optionalUser = userRepository.getUser(address.getUserId());
                    if (optionalUser.isPresent()) {
                        return addressDtoMapper.addressToAddressDto(address, optionalUser);
                    } else {
                        throw new RuntimeException("User not found");
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


}
