package com.griddynamics.user.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.griddynamics.user.dto.ClientDiscountInfoDto;
import com.griddynamics.user.service.Facade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DiscountControllerTest {

    @Mock
    private Facade facade;


    @InjectMocks
    private DiscountController discountController;


    @Test
    void testGetClientDiscountInfo_Success() {
        ClientDiscountInfoDto expectedDto = new ClientDiscountInfoDto();
        when(facade.getClientDiscountInfo(anyLong())).thenReturn(expectedDto);

        ClientDiscountInfoDto actualDto = discountController.getClientDiscountInfo(1L);
        assertNotNull(actualDto);
        verify(facade).getClientDiscountInfo(1L);
    }

    @Test
    void testSetUserVip() {
        doNothing().when(facade).setUserVip(anyLong());

        discountController.setUserVip(1L);
        verify(facade).setUserVip(1L);
    }
}
