package com.griddynamics.user.repository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.griddynamics.user.datasource.QueryHandler;
import com.griddynamics.user.model.Address;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AddressRepositoryTest {

    @Mock
    private QueryHandler<Address> addressQueryHandler;


    @InjectMocks
    private AddressRepository addressRepository;


    @Test
    void testSave() {
        Address address = new Address(1L,1L, "USA", "Times Square", "street", "New York", "NY", "12345", "123456789");
        addressRepository.save(1L, address);
        verify(addressQueryHandler).execute(anyString(), eq(1L), eq("USA"), eq("Times Square"), eq("street"),
                eq("New York"), eq("NY"), eq("12345"), eq("123456789"));
    }

    @Test
    void testDeleteAddress() {
        addressRepository.deleteAddress(1L, 1L);
        verify(addressQueryHandler).execute(anyString(), eq(1L), eq(1L));
    }

    @Test
    void testFindAllByUserId() {
        when(addressQueryHandler.findMany(anyString(), any(), eq(1L)))
                .thenReturn(List.of(new Address(1L, 1L, "USA", "Times Square", "street", "New York", "NY", "12345", "123456789")));

        List<Address> addresses = addressRepository.findAllByUserId(1L);
        assertFalse(addresses.isEmpty());
        assertEquals(1, addresses.size());
        verify(addressQueryHandler).findMany(anyString(), any(), eq(1L));
    }

    @Test
    void testGetNextAddressId() {
        when(addressQueryHandler.findMany(anyString(), any())).thenReturn(Arrays.asList(new Address(), new Address()));
        Long nextId = addressRepository.getNextAddressId();
        assertEquals(2L, nextId);
    }
}
