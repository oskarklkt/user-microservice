package com.griddynamics.user.mapper;

import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.model.Address;
import com.griddynamics.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDtoMapperTest {

    private User user;
    private Address address;
    private AddressDto addressDto;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("John")
                .surname("Doe")
                .email("JohnDoe@gmail.com")
                .gender(Gender.MALE)
                .birthday("01.01.1990")
                .phoneNumber("+1234567890")
                .profilePhotoUrl("https://example.com")
                .build();
        address = Address.builder()
                .id(1L)
                .userId(1L)
                .country("USA")
                .streetAddress("Wall Street")
                .streetAddress2("5")
                .city("New York")
                .stateProvinceRegion("NY")
                .zipCode("10005")
                .phoneNumber("+1234567890")
                .build();
        addressDto = AddressDto.builder()
                .userId(1L)
                .country("USA")
                .name("John")
                .surname("Doe")
                .streetAddress("Wall Street")
                .streetAddress2("5")
                .city("New York")
                .stateProvinceRegion("NY")
                .zipCode("10005")
                .phoneNumber("+1234567890")
                .build();
    }


    @Test
    void apply() {
        assertEquals(addressDto, new AddressDtoMapper().apply(user, address));
    }
}