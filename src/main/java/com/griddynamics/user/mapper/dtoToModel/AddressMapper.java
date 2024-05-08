package com.griddynamics.user.mapper.dtoToModel;

import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.model.Address;
import org.apache.commons.lang3.function.TriFunction;


public class AddressMapper implements TriFunction<Long, Long, AddressDto, Address> {
    @Override
    public Address apply(Long id, Long userId, AddressDto addressDto) {
        return Address.builder()
                .id(id)
                .userId(userId)
                .country(addressDto.getCountry())
                .streetAddress(addressDto.getStreetAddress())
                .streetAddress2(addressDto.getStreetAddress2())
                .city(addressDto.getCity())
                .stateProvinceRegion(addressDto.getStateProvinceRegion())
                .zipCode(addressDto.getZipCode())
                .phoneNumber(addressDto.getPhoneNumber())
                .build();
    }
}
