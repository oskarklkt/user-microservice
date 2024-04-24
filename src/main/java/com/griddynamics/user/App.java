package com.griddynamics.user;

import com.griddynamics.user.controllers.UserController;
import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.enums.Gender;
import com.griddynamics.user.mappers.AddressDtoMapper;
import com.griddynamics.user.mappers.AddressMapper;
import com.griddynamics.user.mappers.UserDtoMapper;
import com.griddynamics.user.mappers.UserMapper;
import com.griddynamics.user.repositories.AddressRepository;
import com.griddynamics.user.repositories.UserRepository;
import com.griddynamics.user.services.AddressService;
import com.griddynamics.user.services.Facade;
import com.griddynamics.user.services.UserService;
import com.griddynamics.user.validator.AddressValidator;
import com.griddynamics.user.validator.UserValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import ui.UserInterface;

@AllArgsConstructor
@Builder
public class App {
    public static void main(String[] args) {
        UserDtoMapper userDtoMapper = new UserDtoMapper();
        AddressDtoMapper addressDtoMapper = new AddressDtoMapper();
        UserMapper userMapper = new UserMapper();
        AddressMapper addressMapper = new AddressMapper();
        UserRepository userRepository = new UserRepository();
        AddressRepository addressRepository = new AddressRepository();
        UserService userService = new UserService(userRepository, userDtoMapper, userMapper);
        AddressService addressService = new AddressService(addressRepository, addressDtoMapper, addressMapper, userRepository);
        Facade facade = new Facade(userService, addressService, new UserValidator(), new AddressValidator());
        UserController userController = new UserController(facade);
        //new UserInterface(userController).start();
        System.out.println(userController.saveUser(UserDto.builder()
                .name("Oskar")
                .surname("Kowalski")
                .email("Oskar@gmail.com")
                .birthday("1999-01-01")
                .profilePhotoUrl("https://www.google.com")
                .phoneNumber("123456789")
                .gender(Gender.MALE)
                .build()));
        System.out.println(userController.getUser(1L));
        System.out.println(userController.getUserByEmail("Oskar@gmail.com"));
        System.out.println(userController.getAllUsers());
        System.out.println(userController.getUserEmail(1L));
        System.out.println(userController.addAddress(1L, AddressDto.builder()
                .city("Warsaw")
                .streetAddress("Marszalkowska")
                        .streetAddress2("1")
                        .country("Poland")
                        .stateProvinceRegion("Mazowieckie")
                        .userId(1L)
                        .name("Oskar")
                        .surname("Klekot")
                        .phoneNumber("500204248")
                        .zipCode("42200")
                        .build()));
        System.out.println(userController.addAddress(1L, AddressDto.builder()
                .city("Cracow")
                .streetAddress("Marszalkowska")
                .streetAddress2("1")
                .country("Poland")
                .stateProvinceRegion("Mazowieckie")
                .userId(1L)
                .name("Oskar")
                .surname("Klekot")
                .phoneNumber("500204248")
                .zipCode("42200")
                .build()));
        System.out.println(userController.getAddresses(1L));
        userController.deleteAddress(1L, 1L);
        System.out.println(userController.getAddresses(1L));
    }
}
