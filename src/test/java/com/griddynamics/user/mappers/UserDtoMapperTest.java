package com.griddynamics.user.mappers;

import com.griddynamics.user.dtos.UserDto;
import com.griddynamics.user.enums.Gender;
import com.griddynamics.user.models.User;
import com.griddynamics.user.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoMapperTest {

    User user;
    UserDto userDto;
    @BeforeEach
    void setUp() {
        user = new User(1L,"test", "Tester", Gender.MALE, "01.01.2000", "+123456789", "test@gmail.com", "url");
        userDto = new UserDto("test", "Tester", Gender.MALE, "01.01.2000", "+123456789", "test@gmail.com", "url");
    }

    @Test
    void userToUserDto() {
        //when
        UserDto userDto = UserDtoMapper.INSTANCE.userToUserDto(user);
        //then
        assertEquals(userDto, this.userDto);
    }

    @Test
    void userDtoToUser() {
        //when
        User user = UserDtoMapper.INSTANCE.userDtoToUser(userDto);
        //it happens in @AfterMapping
        user.setId(UserRepository.getNextId());
        //then
        assertEquals(user, this.user);
    }
}