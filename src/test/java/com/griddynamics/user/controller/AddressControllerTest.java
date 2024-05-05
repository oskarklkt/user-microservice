package com.griddynamics.user.controller;

import com.griddynamics.user.dto.AddressDto;
import com.griddynamics.user.service.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

class AddressControllerTest {

    @Mock
    private Facade facade;

    @InjectMocks
    private AddressController addressController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addAddress() {
        //when
        addressController.addAddress(anyLong(), any());
        //then
        verify(facade).addAddress(anyLong(), any());
    }

    @Test
    void updateAddress() {
        //given
        AddressDto addressDto = new AddressDto();
        //when
        addressController.updateAddress(1L, 1L, addressDto);
        //then
        verify(facade).updateAddress(1L, 1L, addressDto);
    }

    @Test
    void deleteAddress() {
        //when
        addressController.deleteAddress(anyLong(), anyLong());
        //then
        verify(facade).deleteAddress(anyLong(), anyLong());
    }

    @Test
    void getAddresses() {
        //when
        addressController.getAddresses(anyLong());
        //then
        verify(facade).getAddresses(anyLong());
    }

}