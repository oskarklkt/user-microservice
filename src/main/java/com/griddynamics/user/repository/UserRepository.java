package com.griddynamics.user.repository;


import com.griddynamics.user.datasource.QueryHandler;
import com.griddynamics.user.enumeration.ClientType;
import com.griddynamics.user.enumeration.Gender;
import com.griddynamics.user.mapper.resultsetToModel.ResultSetUserMapper;
import com.griddynamics.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

//TODO change to @Repository when we will start Spring data JPA
@Component
@AllArgsConstructor
public class UserRepository {

    private final QueryHandler<User> userQueryHandler;
    private final ResultSetUserMapper resultSetUserMapper;

    private final static String INSERT_USER_QUERY = "INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private final static String GET_USER_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private final static String GET_ALL_USERS_QUERY = "SELECT * FROM users";
    private final static String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private final static String UPDATE_USER_QUERY = "UPDATE users SET name = ?, surname = ?, gender = ?, birthday = ?, phone_number = ?, email = ?, profile_photo_url = ? WHERE id = ?";
    private final static String SET_USER_VIP_QUERY = "UPDATE users SET client_type = ? WHERE id = ?";

    public void save(User user) {
        userQueryHandler.execute(INSERT_USER_QUERY,
                user.getName(), user.getSurname(), user.getGender().equals(Gender.FEMALE) ? "F" : "M",
                user.getBirthday(), user.getPhoneNumber(), user.getEmail(),
                user.getProfilePhotoUrl(), String.valueOf(java.time.LocalDate.now()), ClientType.BASIC.toString());
    }

    public Optional<User> getUser(Long id) {
        return Optional.ofNullable(userQueryHandler.findOne(GET_USER_BY_ID_QUERY, resultSetUserMapper, id));
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userQueryHandler.findOne(GET_USER_BY_EMAIL_QUERY, resultSetUserMapper, email));
    }

    public List<User> getAllUsers() {
        return userQueryHandler.findMany(GET_ALL_USERS_QUERY, resultSetUserMapper);
    }

    public void deleteUser(Long userId) {
        userQueryHandler.execute(DELETE_USER_QUERY, userId);
    }

    public User updateUser(Long userId, User user) {
        userQueryHandler.execute(UPDATE_USER_QUERY,
                user.getName(), user.getSurname(), user.getGender().equals(Gender.FEMALE) ? "F" : "M", user.getBirthday(), user.getPhoneNumber(), user.getEmail(), user.getProfilePhotoUrl(), userId);
        return user;
    }

    public Long getNextId() {
        return (long) (userQueryHandler.findMany(GET_ALL_USERS_QUERY, resultSetUserMapper).size() + 1);
    }

    public void setUserVip(Long userId) {
        userQueryHandler.execute(SET_USER_VIP_QUERY, ClientType.VIP.toString(), userId);
    }

}
