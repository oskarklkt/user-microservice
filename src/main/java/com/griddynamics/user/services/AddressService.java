package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.mappers.AddressDtoMapper;
import com.griddynamics.user.models.Address;
import com.griddynamics.user.repositories.AddressRepository;
import com.griddynamics.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;
import java.util.List;

@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoMapper addressDtoMapper;
    private final UserRepository userRepository;

    public void addAddress(Long userId, AddressDto addressDto) {
        Address address = addressDtoMapper.addressDtoToAddress(addressDto);
        addressRepository.save(userId, address);
    }

    public void updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        Address address = addressDtoMapper.addressDtoToAddress(addressDto);
        addressRepository.deleteAddress(userId, addressId);
        addressRepository.save(userId, address);
    }

    public void deleteAddress(Long userId, Long addressId) {
        addressRepository.deleteAddress(userId, addressId);
    }

    public List<AddressDto> getAddresses(Long userId) {
        return addressRepository.findAllByUserId(userId)
                .stream()
                .map(address -> {
                    var user = userRepository.getUser(address.getUserId());
                    return addressDtoMapper.addressToAddressDto(address, user);
                })
                .collect(Collectors.toList());
    }


}
