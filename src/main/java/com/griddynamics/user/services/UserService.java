package com.griddynamics.user.services;

import com.griddynamics.user.repositories.UserRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;


}
