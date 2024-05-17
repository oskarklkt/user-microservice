package com.griddynamics.user.mapper.modelToDto;

import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.model.Address;
import com.griddynamics.user.model.User;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
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
