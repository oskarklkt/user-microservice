package com.griddynamics.user.repository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.griddynamics.user.datasource.QueryHandler;
import com.griddynamics.user.enumeration.ClientType;
import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private QueryHandler<User> userQueryHandler;


    @InjectMocks
    private UserRepository userRepository;


    @Test
    void testSave() {
        User user = new User(1L, "John", "Doe", Gender.MALE, "1990-01-01", "123456789", "john.doe@example.com", "https://image.url", "2024-05-09", ClientType.BASIC);
        userRepository.save(user);
        verify(userQueryHandler).execute(eq("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"), eq("John"), eq("Doe"), eq("M"), eq("1990-01-01"), eq("123456789"), eq("john.doe@example.com"), eq("https://image.url"), eq("2024-05-09"), eq("BASIC"));
    }

    @Test
    void testGetUser() {
        User expectedUser = new User(1L, "John", "Doe", Gender.MALE, "1990-01-01", "123456789", "john.doe@example.com", "https://image.url", "2023-01-01", ClientType.BASIC);
        when(userQueryHandler.findOne(anyString(), any(), eq(1L))).thenReturn(expectedUser);
        Optional<User> user = userRepository.getUser(1L);
        assertTrue(user.isPresent());
        assertEquals("John", user.get().getName());
        verify(userQueryHandler).findOne(anyString(), any(), eq(1L));
    }

    @Test
    void testGetUserByEmail() {
        User expectedUser = new User(1L, "John", "Doe", Gender.MALE, "1990-01-01", "123456789", "john.doe@example.com", "https://image.url", "2023-01-01", ClientType.BASIC);
        when(userQueryHandler.findOne(anyString(), any(), eq("john.doe@example.com"))).thenReturn(expectedUser);
        Optional<User> user = userRepository.getUserByEmail("john.doe@example.com");
        assertTrue(user.isPresent());
        assertEquals("John", user.get().getName());
        verify(userQueryHandler).findOne(anyString(), any(), eq("john.doe@example.com"));
    }

    @Test
    void testGetAllUsers() {
        List<User> expectedUsers = List.of(new User(1L, "John", "Doe", Gender.MALE, "1990-01-01", "123456789", "john.doe@example.com", "https://image.url", "2023-01-01", ClientType.BASIC));
        when(userQueryHandler.findMany(anyString(), any())).thenReturn(expectedUsers);
        List<User> users = userRepository.getAllUsers();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userQueryHandler).findMany(anyString(), any());
    }

    @Test
    void testDeleteUser() {
        userRepository.deleteUser(1L);
        verify(userQueryHandler).execute(anyString(), eq(1L));
    }

    @Test
    void testUpdateUser() {
        User user = new User(1L, "John", "Doe", Gender.MALE, "1990-01-01", "123456789", "john.doe@example.com", "https://image.url", "2023-01-01", ClientType.BASIC);
        User updatedUser = userRepository.updateUser(1L, user);
        verify(userQueryHandler).execute(anyString(), eq("John"), eq("Doe"), eq("M"), eq("1990-01-01"), eq("123456789"), eq("john.doe@example.com"), eq("https://image.url"), eq(1L));
        assertEquals(user.getEmail(), updatedUser.getEmail());  // Confirm the returned user has expected fields
    }

    @Test
    void testSetUserVip() {
        userRepository.setUserVip(1L);
        verify(userQueryHandler).execute(anyString(), eq("VIP"), eq(1L));
    }
}