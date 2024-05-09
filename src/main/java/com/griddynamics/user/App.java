package com.griddynamics.user;

import com.griddynamics.user.common.AddressQueryHandler;
import com.griddynamics.user.common.Database;
import com.griddynamics.user.common.UserQueryHandler;
import com.griddynamics.user.controller.AddressController;
import com.griddynamics.user.controller.DiscountController;
import com.griddynamics.user.controller.UserController;
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
        UserQueryHandler userQueryHandler = new UserQueryHandler();
        ResultSetAddressMapper resultSetAddressMapper = new ResultSetAddressMapper();
        ResultSetUserMapper resultSetUserMapper = new ResultSetUserMapper();
        AddressDtoMapper addressDtoMapper = new AddressDtoMapper();
        UserMapper userMapper = new UserMapper();
        AddressMapper addressMapper = new AddressMapper();
        UserRepository userRepository = new UserRepository(userQueryHandler, resultSetUserMapper);
        AddressQueryHandler addressQueryHandler = new AddressQueryHandler();
        AddressRepository addressRepository = new AddressRepository(addressQueryHandler, resultSetAddressMapper);
        ClientDiscountInfoDtoMapper clientDiscountInformationDtoMapper = new ClientDiscountInfoDtoMapper();
        UserService userService = new UserService(userRepository, userDtoMapper, userMapper);
        AddressService addressService = new AddressService(addressRepository, addressDtoMapper, addressMapper, userRepository);
        Facade facade = new Facade(userService, addressService, new UserValidator(), new AddressValidator(), new DiscountInfoService(userRepository, clientDiscountInformationDtoMapper));
        UserController userController = new UserController(facade);
        AddressController addressController = new AddressController(facade);
        DiscountController discountController = new DiscountController(facade);

        Dotenv dotenv = Dotenv.load();
        Database.initialize(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
    }
}
