package com.griddynamics.user.controller;

import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.dto.ClientDiscountInfoDto;
import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.exception.BaseException;
import com.griddynamics.user.service.Facade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class UserController {
    private final Facade facade;

    public UserDto saveUser(UserDto userDto) {
        try {
            return facade.saveUser(userDto);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public UserDto getUser(Long id) {
        try {
            return facade.getUser(id);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public UserDto getUserByEmail(String email) {
        try {
            return facade.getUserByEmail(email);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public List<UserDto> getAllUsers() {
        try {
            return facade.getAllUsers();
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public String getUserEmail(Long userId) {
        try {
            return facade.getUserEmail(userId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public void deleteUser(Long userId) {
        try {
            facade.deleteUser(userId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        try {
            return facade.updateUser(userId, userDto);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }


    public boolean isEmailInDatabase(String email) {
        return facade.isEmailInDatabase(email);
    }

    public UserDto isUserInDatabase(Long userId) {
        try {
            return facade.getUser(userId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public AddressDto addAddress(Long userId, AddressDto addressDto) {
        try {
            return facade.addAddress(userId, addressDto);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public AddressDto updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        try {
            return facade.updateAddress(userId, addressId, addressDto);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public void deleteAddress(Long userId, Long addressId) {
        try {
            facade.deleteAddress(userId, addressId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
    }

    public List<AddressDto> getAddresses(Long userId) {
        try {
            return facade.getAddresses(userId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public ClientDiscountInfoDto getClientDiscountInfo(Long userId) {
        try {
            return facade.getClientDiscountInfo(userId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public void setUserVip(Long userId) {
        try {
            facade.setUserVip(userId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
    }
}
