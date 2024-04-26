package com.griddynamics.user.validator;

import com.griddynamics.user.dto.UserDto;
import com.griddynamics.user.enumeration.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    private UserValidator userValidator;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userValidator = new UserValidator();
    }

    @Test
    void isUserDtoValid_WithValidUserDto_ReturnsTrue() {
        UserDto validUserDto = UserDto.builder()
                .email("test@example.com")
                .phoneNumber("+1234567890")
                .name("John")
                .surname("Doe")
                .gender(Gender.MALE)
                .birthday("1990-01-01")
                .build();

        assertTrue(userValidator.isUserDtoValid(validUserDto));
    }

    @Test
    void isUserDtoValid_WithInvalidEmail_ReturnsFalse() {
        UserDto userDto = UserDto.builder()
                .email("invalid-email")
                .phoneNumber("+1234567890")
                .name("John")
                .surname("Doe")
                .gender(Gender.MALE)
                .birthday("1990-01-01")
                .build();

        assertFalse(userValidator.isUserDtoValid(userDto));
    }

    // Additional tests for other fields...
    @Test
    void isUserDtoValid_WithInvalidPhoneNumber_ReturnsFalse() {
        UserDto userDto = UserDto.builder()
                .email("test@example.com")
                .phoneNumber("123")
                .name("John")
                .surname("Doe")
                .gender(Gender.MALE)
                .birthday("1990-01-01")
                .build();

        assertFalse(userValidator.isUserDtoValid(userDto));
    }

    @Test
    void isUserDtoValid_WithInvalidName_ReturnsFalse() {
        UserDto userDto = UserDto.builder()
                .email("test@example.com")
                .phoneNumber("+1234567890")
                .name("12345")
                .surname("Doe")
                .gender(Gender.MALE)
                .birthday("1990-01-01")
                .build();

        assertFalse(userValidator.isUserDtoValid(userDto));
    }

    @Test
    void isUserDtoValid_WithInvalidSurname_ReturnsFalse() {
        UserDto userDto = UserDto.builder()
                .email("test@example.com")
                .phoneNumber("+1234567890")
                .name("John")
                .surname("12345")
                .gender(Gender.MALE)
                .birthday("1990-01-01")
                .build();

        assertFalse(userValidator.isUserDtoValid(userDto));
    }

    @Test
    void isUserDtoValid_WithInvalidBirthday_ReturnsFalse() {
        UserDto userDto = UserDto.builder()
                .email("test@example.com")
                .phoneNumber("+1234567890")
                .name("John")
                .surname("Doe")
                .gender(Gender.MALE)
                .birthday("90-01-01")
                .build();

        assertFalse(userValidator.isUserDtoValid(userDto));
    }

}