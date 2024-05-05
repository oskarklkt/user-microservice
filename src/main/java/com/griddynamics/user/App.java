package com.griddynamics.user;

import com.griddynamics.user.controller.AddressController;
import com.griddynamics.user.controller.DiscountController;
import com.griddynamics.user.controller.UserController;
import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.mapper.*;
import com.griddynamics.user.repository.AddressRepository;
import com.griddynamics.user.repository.UserRepository;
import com.griddynamics.user.service.AddressService;
import com.griddynamics.user.service.Facade;
import com.griddynamics.user.service.UserService;
import com.griddynamics.user.validator.AddressValidator;
import com.griddynamics.user.validator.UserValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Builder
public class App {
    @Generated
    public static void main(String[] args) {
        UserDtoMapper userDtoMapper = new UserDtoMapper();
        AddressDtoMapper addressDtoMapper = new AddressDtoMapper();
        UserMapper userMapper = new UserMapper();
        AddressMapper addressMapper = new AddressMapper();
        UserRepository userRepository = new UserRepository();
        AddressRepository addressRepository = new AddressRepository();
        ClientDiscountInfoDtoMapper clientDiscountInformationDtoMapper = new ClientDiscountInfoDtoMapper();
        UserService userService = new UserService(userRepository, userDtoMapper, userMapper, clientDiscountInformationDtoMapper);
        AddressService addressService = new AddressService(addressRepository, addressDtoMapper, addressMapper, userRepository);
        Facade facade = new Facade(userService, addressService, new UserValidator(), new AddressValidator());
        UserController userController = new UserController(facade);
        AddressController addressController = new AddressController(facade);
        DiscountController discountController = new DiscountController(facade);
        log.info("{}", userController.saveUser(UserDto.builder()
                .name("Oskar")
                .surname("Kowalski")
                .email("Oskar@gmail.com")
                .birthday("1999-01-01")
                .profilePhotoUrl("https://www.google.com")
                .phoneNumber("123456789")
                .gender(Gender.MALE)
                .build()));
        log.info("{}", userController.getUser(1L));
        log.info("{}", userController.getUserByEmail("Oskar@gmail.com"));
        log.info("{}", userController.getAllUsers());
        log.info("{}", userController.getUserEmail(1L));
        log.info("{}", addressController.addAddress(1L, AddressDto.builder()
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
        log.info("{}", addressController.addAddress(1L, AddressDto.builder()
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
        log.info("{}", addressController.getAddresses(1L));
        addressController.deleteAddress(1L, 1L);
        log.info("{}", addressController.getAddresses(1L));
        log.info("{}", discountController.getClientDiscountInfo(1L));
        discountController.setUserVip(1L);
        log.info("{}", discountController.getClientDiscountInfo(1L));
    }
}
