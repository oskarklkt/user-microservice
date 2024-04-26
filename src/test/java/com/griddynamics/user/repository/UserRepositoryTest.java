package com.griddynamics.user.repository;

import com.griddynamics.user.enumeration.ClientType;
import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    UserRepository userRepository;
    User user;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        user = new User(1L,"test", "Tester", Gender.MALE, "01.01.2000", "+123456789", "test@gmail.com", "url", "01.01.2021", ClientType.BASIC );
    }

    @AfterEach
    void tearDown() {
        UserRepository.getUsers().clear();
    }

    @Test
    void save() {
        //when
        userRepository.save(user);
        //then
        assertTrue(UserRepository.getUsers().containsKey(1L));
        assertTrue(UserRepository.getUsers().containsValue(user));
    }

    @Test
    void getUser() {
        //given
        userRepository.save(user);
        //when
        User userFromDb = userRepository.getUser(1L).get();
        //then
        assertEquals(user, userFromDb);
        //then
        assertEquals(user, userFromDb);
    }

    @Test
    void getUserByEmail() {
        //given
        userRepository.save(user);
        //when
        User userFromDb = userRepository.getUserByEmail("test@gmail.com").get();
        // then
        assertEquals(user, userFromDb);
    }

    @Test
    void getAllUsers() {
        //given
        userRepository.save(user);
        //when
        userRepository.save(new User(1L,"test", "Tester", Gender.MALE, "01.01.2000", "+1234w56789", "test@gmail.com", "url", "01.01.2021", ClientType.BASIC));
        //then
        assertEquals(2, userRepository.getAllUsers().size());
    }

    @Test
    void deleteUser() {
        //given
        userRepository.save(user);
        //when
        userRepository.deleteUser(1L);
        //then
        assertFalse(UserRepository.getUsers().containsKey(1L));
    }

    @Test
    void updateUser() {
        //given
        userRepository.save(user);
        //when
        User updatedUser = new User(1L,"test33", "Tester", Gender.MALE, "01.01.2000", "+123456789", "test@gmail.com", "url", "01.01.2021", ClientType.BASIC);
        userRepository.updateUser(1L, updatedUser);
        //then
        assertEquals(updatedUser, userRepository.getUser(1L).get());
    }

    @Test
    void isEmailInDatabase() {
        //given
        userRepository.save(user);
        //then
        assertTrue(userRepository.isEmailInDatabase("test@gmail.com"));
        assertFalse(userRepository.isEmailInDatabase("ndioasndio"));
    }

    @Test
    void isUserInDatabase() {
        //given
        userRepository.save(user);
        //then
        assertTrue(userRepository.isUserInDatabase(1L));
        assertFalse(userRepository.isUserInDatabase(2L));
    }

    @Test
    void getNextId() {
        //given
        userRepository.save(user);
        //then
        assertEquals(2L, UserRepository.getNextId());
    }

    @Test
    void getUsers() {
        //given
        userRepository.save(user);
        //then
        assertEquals(1, UserRepository.getUsers().size());
    }
}