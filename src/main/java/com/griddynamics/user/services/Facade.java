package com.griddynamics.user.services;

import com.griddynamics.user.dtos.AddressDto;
import com.griddynamics.user.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Facade {
    private final UserService userService;
    private final AddressService addressService;

    public boolean saveUser(UserDto userDto) {
        return userService.saveUser(userDto);
    }

    public Optional<UserDto> getUser(Long id) {
        return userService.getUser(id);
    }

    public UserDto getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    public String getUserEmail(Long userId) {
        return userService.getUserEmail(userId);
    }

    public boolean deleteUser(Long userId) {
        if (!userService.isUserInDatabase(userId)) {
            return false;
        }
        userService.deleteUser(userId);
        return true;
    }

    public boolean updateUser(Long userId, UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }


    public boolean isEmailInDatabase(String email) {
        return userService.isEmailInDatabase(email);
    }

    public boolean isUserInDatabase(Long userId) {
        return userService.isUserInDatabase(userId);
    }

    public boolean addAddress(Long userId, AddressDto addressDto) {
        return addressService.addAddress(userId, addressDto);
    }

    public boolean updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        return addressService.updateAddress(userId, addressId, addressDto);
    }

    public boolean deleteAddress(Long userId, Long addressId) {
        return addressService.deleteAddress(userId, addressId);
    }

    public Optional<List<AddressDto>> getAddresses(Long userId) {
        List<AddressDto> addressesDto = addressService.getAddresses(userId);
        Optional<UserDto> userDto = userService.getUser(userId);
        if (addressesDto == null || userDto.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(addressesDto.stream()
                    .map(addressDto -> new AddressDto(
                            userId,
                            addressDto.getCountry(),
                            userDto.get().getName(),
                            userDto.get().getSurname(),
                            addressDto.getStreetAddress(),
                            addressDto.getStreetAddress2(),
                            addressDto.getCity(),
                            addressDto.getStateProvinceRegion(),
                            addressDto.getZipCode(),
                            addressDto.getPhoneNumber()))
                    .collect(Collectors.toList()));
        }
    }
}
