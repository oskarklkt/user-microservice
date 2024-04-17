package com.griddynamics.user.services;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Facade {
    private final UserService userService;
    private final AddressService addressService;

}
