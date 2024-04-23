package com.griddynamics.user.validator;

import com.griddynamics.user.dtos.AddressDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressValidatorTest {

    private AddressValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AddressValidator();
    }

    @Test
    void validateAddress_WhenValid_ReturnsTrue() {
        AddressDto addressDto = new AddressDto(1L, "Usa", "John", "Doe", "123 Main St",
                "street2", "new", "some", "44323", "10001");
        assertTrue(validator.validateAddress(addressDto));
    }

    @Test
    void validateAddress_WhenInvalidAddress_ReturnsFalse() {
        AddressDto addressDto = new AddressDto(1L, "", "John", "Doe", "123 Main St",
                "street2", "new", "some", "44323", "10001");
        assertFalse(validator.validateAddress(addressDto));
    }
}