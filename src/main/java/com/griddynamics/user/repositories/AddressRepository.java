package com.griddynamics.user.repositories;

import com.griddynamics.user.models.Address;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AddressRepository {
    @Getter
    private static final Map<Long, List<Address>> addresses = new ConcurrentHashMap<>();

    public void save(Long userId, Address address) {
        addresses.computeIfAbsent(userId, k -> new ArrayList<>()).add(address);
    }

    public void deleteAddress(Long userId, Long addressId) {
        List<Address> userAddresses = addresses.get(userId);
        if (userAddresses != null) {
            userAddresses.removeIf(address -> address.getId().equals(addressId));
        }
    }

    public List<Address> findAllByUserId(Long userId) {
        return addresses.getOrDefault(userId, Collections.emptyList());
    }

    public static Long getNextAddressId() {
        return AddressRepository.addresses.values().stream()
                .mapToLong(List::size)
                .sum() + 1L;
    }

    public boolean isAddressInDatabase(Long userId, Long addressId) {
        List<Address> userAddresses = addresses.get(userId);
        return userAddresses != null && userAddresses.stream()
                .anyMatch(address -> address.getId().equals(addressId));
    }
}