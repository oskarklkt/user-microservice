package com.griddynamics.user;

import com.griddynamics.user.controllers.UserController;
import com.griddynamics.user.mappers.AddressDtoMapper;
import com.griddynamics.user.mappers.UserDtoMapper;
import com.griddynamics.user.repositories.AddressRepository;
import com.griddynamics.user.repositories.UserRepository;
import com.griddynamics.user.services.AddressService;
import com.griddynamics.user.services.Facade;
import com.griddynamics.user.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import ui.UserInterface;

@AllArgsConstructor
@Builder
public class App {
    public static void main(String[] args) {
        UserDtoMapper userDtoMapper = UserDtoMapper.INSTANCE;
        AddressDtoMapper addressDtoMapper = AddressDtoMapper.INSTANCE;
        UserRepository userRepository = new UserRepository();
        AddressRepository addressRepository = new AddressRepository();
        UserService userService = new UserService(userRepository, userDtoMapper);
        AddressService addressService = new AddressService(addressRepository, addressDtoMapper, userRepository);
        Facade facade = new Facade(userService, addressService);
        UserController userController = new UserController(facade);
        UserInterface userInterface = new UserInterface(userController);
        userInterface.start();
    }
}
