package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FacadeTest {

    UserService userService;
    AddressService addressService;
    UserDto userDto;
    AddressDto addressDto;
    Facade facade;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        addressService = Mockito.mock(AddressService.class);
        userDto = Mockito.mock(UserDto.class);
        addressDto = Mockito.mock(AddressDto.class);
        facade = new Facade(userService, addressService);
    }

    @Test
    void saveUser() {
        //when
        facade.saveUser(userDto);
        //then
        Mockito.verify(userService).saveUser(userDto);
    }

    @Test
    void getUser() {
        //when
        facade.getUser(1L);
        //then
        Mockito.verify(userService).getUser(1L);
    }

    @Test
    void getUserByEmail() {
        //when
        facade.getUserByEmail("email");
        //then
        Mockito.verify(userService).getUserByEmail("email");
    }

    @Test
    void getAllUsers() {
        //when
        facade.getAllUsers();
        //then
        Mockito.verify(userService).getAllUsers();
    }

    @Test
    void getUserEmail() {
        //when
        facade.getUserEmail(1L);
        //then
        Mockito.verify(userService).getUserEmail(1L);
    }

    @Test
    void updateUser() {
        //when
        facade.updateUser(1L, userDto);
        //then
        Mockito.verify(userService).updateUser(1L, userDto);
    }

    @Test
    void isEmailInDatabase() {
        //when
        facade.isEmailInDatabase("email");
        //then
        Mockito.verify(userService).isEmailInDatabase("email");
    }

    @Test
    void isUserInDatabase() {
        //when
        facade.isUserInDatabase(1L);
        //then
        Mockito.verify(userService).isUserInDatabase(1L);
    }

    @Test
    void addAddress() {
        //when
        facade.addAddress(1L, addressDto);
        //then
        Mockito.verify(addressService).addAddress(1L, addressDto);
    }

    @Test
    void updateAddress() {
        //when
        facade.updateAddress(1L, 1L, addressDto);
        //then
        Mockito.verify(addressService).updateAddress(1L, 1L, addressDto);
    }

    @Test
    void deleteAddress() {
        //when
        facade.deleteAddress(1L, 1L);
        //then
        Mockito.verify(addressService).deleteAddress(1L, 1L);
    }

    @Test
    void getAddresses() {
        // Arrange
        Long userId = 1L;
        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setSurname("Doe");

        AddressDto addressDto = new AddressDto();
        addressDto.setCountry("Country");
        addressDto.setStreetAddress("Street");
        addressDto.setStreetAddress2("Street2");
        addressDto.setCity("City");
        addressDto.setStateProvinceRegion("State");
        addressDto.setZipCode("Zip");
        addressDto.setPhoneNumber("Phone");

        List<AddressDto> addressesDto = List.of(addressDto);

        Mockito.when(userService.getUser(userId)).thenReturn(Optional.of(userDto));
        Mockito.when(addressService.getAddresses(userId)).thenReturn(addressesDto);

        // Act
        Optional<List<AddressDto>> result = facade.getAddresses(userId);

        // Assert
        assertEquals(1, result.get().size());
        assertEquals(userDto.getName(), result.get().get(0).getName());
        assertEquals(userDto.getSurname(), result.get().get(0).getSurname());
        assertEquals(addressDto.getCountry(), result.get().get(0).getCountry());
        assertEquals(addressDto.getStreetAddress(), result.get().get(0).getStreetAddress());
        assertEquals(addressDto.getStreetAddress2(), result.get().get(0).getStreetAddress2());
        assertEquals(addressDto.getCity(), result.get().get(0).getCity());
        assertEquals(addressDto.getStateProvinceRegion(), result.get().get(0).getStateProvinceRegion());
        assertEquals(addressDto.getZipCode(), result.get().get(0).getZipCode());
        assertEquals(addressDto.getPhoneNumber(), result.get().get(0).getPhoneNumber());

        Mockito.verify(userService).getUser(userId);
        Mockito.verify(addressService).getAddresses(userId);
    }
}