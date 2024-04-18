package com.griddynamics.user.repositories;

import com.griddynamics.user.models.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AddressRepositoryTest {

    AddressRepository addressRepository;
    Address address;

    @BeforeEach
    void setUp() {
        address = new Address(1L, 1L, "Poland", "Marszałkowska", "1", "Warsaw", "Mazovian", "00-000", "123456789");
        addressRepository = new AddressRepository();
    }

    @AfterEach
    void tearDown() {
        AddressRepository.getAddresses().clear();
    }

    @Test
    void save() {
        //when
        addressRepository.save(1L, address);
        //then
        assertEquals(1, AddressRepository.getAddresses().size());
    }

    @Test
    void saveTwo() {
        //given
        Address address1 = new Address(1L, 1L, "Poland", "3 Maja", "1", "Czestochowa", "Slaskie", "00-000", "123456789");
        //when
        addressRepository.save(1L, address);
        addressRepository.save(1L, address1);
        //then
        assertEquals(2, AddressRepository.getAddresses().get(1L).size());
    }

    @Test
    void deleteAddress() {
        //given
        addressRepository.save(1L, address);
        //when
        addressRepository.deleteAddress(1L, 1L);
        //then
        assertEquals(0, AddressRepository.getAddresses().get(1L).size());
    }

    @Test
    void findAllByUserId() {
        //given
        addressRepository.save(1L, address);
        //when
        var addresses = addressRepository.findAllByUserId(1L);
        //then
        assertEquals(1, addresses.size());
    }
}