package com.griddynamics.user.mappers;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.enums.Gender;
import com.griddynamics.user.models.Address;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDtoMapperTest {

    Address address;
    AddressDto addressDto;
    UserRepository userRepository;
    User user;

    @BeforeEach
    void setUp() {
        address = new Address(1L, 1L, "test", "test", "test", "test", "test", "test", "test");
        addressDto = new AddressDto(1L, "test", "test", "test", "test", "test", "test", "test", "test", "test");
        AddressDto addressDto1 = AddressDto.builder()
                .country(
                ""
                )
                .country("")
                .build();

        user = new User(1L, "test", "test",Gender.MALE, "test", "test", "test", "test");
        userRepository = new UserRepository();
        UserRepository.getUsers().put(1L, user);
    }

    @AfterEach
    void tearDown() {
        UserRepository.getUsers().clear();
    }
    @Test
    void addressToAddressDto() {
        AddressDto addressDto1 = AddressDtoMapper.INSTANCE.addressToAddressDto(address, userRepository.getUser(1L));
        System.out.println(addressDto1.toString());
        System.out.println(addressDto.toString());
        assertEquals(addressDto1, addressDto);
    }

    @Test
    void addressDtoToAddress() {
        Address address1 = AddressDtoMapper.INSTANCE.addressDtoToAddress(addressDto);
        System.out.println(address1.toString());
        System.out.println(address.toString());
        assertEquals(address1, address);
    }

    @Test
    void extractName() {
        String name = AddressDtoMapper.INSTANCE.extractName(address);
        assertEquals(name, "test");
    }

    @Test
    void extractSurname() {
        String surname = AddressDtoMapper.INSTANCE.extractSurname(address, userRepository.getUser(1L));
        assertEquals(surname, "test");
    }

    @Test
    void setAddressId() {
        Address address1 = new Address(1L, 1L, "test", "test", "test", "test", "test", "test", "test");
        AddressDtoMapper.INSTANCE.setAddressId(address1);
        assertEquals(address1.getId(), 1L);
    }
}