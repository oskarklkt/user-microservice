package com.griddynamics.user.repositories;

import com.griddynamics.user.models.Address;
import lombok.Getter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressRepository {
    @Getter
    private static final Map<Long, List<Address>> addresses = new HashMap<>();

    public void save(Long userId, Address address) {
        if (addresses.get(userId) == null) {
            addresses.put(userId, new ArrayList<>(List.of(address)));
        } else {
            addresses.get(userId).add(address);
        }
    }

    public void deleteAddress(Long userId, Long addressId) {
        addresses.get(userId).removeIf(address -> address.getId().equals(addressId));
    }

    public List<Address> findAllByUserId(Long userId) {
        return addresses.get(userId);
    }

    public static Long getNextAddressId() {
        return addresses.values().stream()
                .mapToLong(List::size)
                .sum() + 1L;
    }

    public boolean isAddressInDatabase(Long userId, Long addressId) {
        return addresses.get(userId).stream()
                .anyMatch(address -> address.getId().equals(addressId));
    }
}
