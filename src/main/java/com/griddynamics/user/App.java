package com.griddynamics.user;

import com.griddynamics.user.common.AddressQueryHandler;
import com.griddynamics.user.common.Database;
import com.griddynamics.user.common.UserQueryHandler;
import com.griddynamics.user.controller.AddressController;
import com.griddynamics.user.controller.DiscountController;
import com.griddynamics.user.controller.UserController;
import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.mapper.dtoToModel.AddressMapper;
import com.griddynamics.user.mapper.dtoToModel.UserMapper;
import com.griddynamics.user.mapper.modelToDto.AddressDtoMapper;
import com.griddynamics.user.mapper.modelToDto.ClientDiscountInfoDtoMapper;
import com.griddynamics.user.mapper.modelToDto.UserDtoMapper;
import com.griddynamics.user.mapper.resultsetToModel.ResultSetAddressMapper;
import com.griddynamics.user.mapper.resultsetToModel.ResultSetUserMapper;
import com.griddynamics.user.repository.AddressRepository;
import com.griddynamics.user.repository.UserRepository;
import com.griddynamics.user.service.AddressService;
import com.griddynamics.user.service.DiscountInfoService;
import com.griddynamics.user.service.Facade;
import com.griddynamics.user.service.UserService;
import com.griddynamics.user.validator.AddressValidator;
import com.griddynamics.user.validator.UserValidator;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Builder
public class App {
    @SneakyThrows
    @Generated
    public static void main(String[] args) {
        UserDtoMapper userDtoMapper = new UserDtoMapper();
        ResultSetAddressMapper resultSetAddressMapper = new ResultSetAddressMapper();
        ResultSetUserMapper resultSetUserMapper = new ResultSetUserMapper();
        AddressDtoMapper addressDtoMapper = new AddressDtoMapper();
        UserMapper userMapper = new UserMapper();
        AddressMapper addressMapper = new AddressMapper();
        UserRepository userRepository = new UserRepository();
        AddressRepository addressRepository = new AddressRepository();
        ClientDiscountInfoDtoMapper clientDiscountInformationDtoMapper = new ClientDiscountInfoDtoMapper();
        UserService userService = new UserService(userRepository, userDtoMapper, userMapper);
        AddressService addressService = new AddressService(addressRepository, addressDtoMapper, addressMapper, userRepository);
        Facade facade = new Facade(userService, addressService, new UserValidator(), new AddressValidator(), new DiscountInfoService(userRepository, clientDiscountInformationDtoMapper));
        UserController userController = new UserController(facade);
        AddressController addressController = new AddressController(facade);
        DiscountController discountController = new DiscountController(facade);
        UserQueryHandler userQueryHandler = new UserQueryHandler();
        AddressQueryHandler addressQueryHandler = new AddressQueryHandler();
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

        /*
        Database.execute("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                "test", "tester", "M", "1990-01-01", "123456789", "test@ex.com", "https://www.google.com", "2021-01-01", "BASIC");

        Database.execute("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                preparedStatement -> {
                    try {
                        preparedStatement.setString(1, "test2");
                        preparedStatement.setString(2, "tester2");
                        preparedStatement.setString(3, "M");
                        preparedStatement.setString(4, "1990-01-01");
                        preparedStatement.setString(5, "123456789");
                        preparedStatement.setString(6, "test@ex2.com");
                        preparedStatement.setString(7, "https://www.google.com");
                        preparedStatement.setString(8, "2021-01-01");
                        preparedStatement.setString(9, "BASIC");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
        */

        Dotenv dotenv = Dotenv.load();
        Database.initialize(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
        log.info("{}", userQueryHandler.findOne("SELECT * FROM users WHERE id = ?", resultSetUserMapper, 1L));
        log.info("{}", addressQueryHandler.findOne("SELECT * FROM addresses WHERE id = ?", resultSetAddressMapper, 1L));
        log.info("{}", userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper));
        log.info("{}", addressQueryHandler.findMany("SELECT * FROM addresses", resultSetAddressMapper));
    }
}
