package com.griddynamics.user.repository;


import com.griddynamics.user.enumeration.ClientType;
import com.griddynamics.user.model.User;
import lombok.Getter;

import java.util.*;

public class UserRepository {
    @Getter
    private static final Map<Long, User> users = new HashMap<>();

    public void save(User user) {
        user.setId(getNextId());
        user.setDateOfAccountCreation(String.valueOf(java.time.LocalDate.now()));
        user.setClientType(ClientType.BASIC);
        users.put(user.getId(), user);
    }

    public Optional<User> getUser(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteUser(Long userId) {
        users.remove(userId);
    }

    public User updateUser(Long userId, User user) {
        users.remove(userId);
        user.setId(userId);
        users.put(userId, user);
        return user;
    }

    public boolean isEmailInDatabase(String email) {
        return users.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    public boolean isUserInDatabase(Long userId) {
        return users.containsKey(userId);
    }

    public static Long getNextId() {
        return users.size() + 1L;
    }

    public void setUserVip(Long userId) {
        User user = users.get(userId);
        user.setClientType(ClientType.VIP);
        users.put(userId, user);
    }
}
