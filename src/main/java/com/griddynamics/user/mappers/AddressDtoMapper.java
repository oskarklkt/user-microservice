package com.griddynamics.user.mappers;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.models.Address;
import com.griddynamics.user.models.User;

import java.util.function.BiFunction;

public class AddressDtoMapper implements BiFunction<User, Address, AddressDto> {
    @Override
    public AddressDto apply(User user, Address address) {
        return AddressDto.builder()
                .userId(user.getId())
                .country(address.getCountry())
                .name(user.getName())
                .surname(user.getSurname())
                .streetAddress(address.getStreetAddress())
                .streetAddress2(address.getStreetAddress2())
                .city(address.getCity())
                .stateProvinceRegion(address.getStateProvinceRegion())
                .zipCode(address.getZipCode())
                .phoneNumber(address.getPhoneNumber())
                .build();
    }
}
