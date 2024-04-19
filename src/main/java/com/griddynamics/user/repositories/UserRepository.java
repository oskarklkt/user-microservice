package com.griddynamics.user.repositories;


import com.griddynamics.user.models.User;
import lombok.Getter;

import java.util.*;

public class UserRepository {
    @Getter
    private static final Map<Long, User> users = new HashMap<>();

    public void save(User user) {
        user.setId(getNextId());
        users.put(getNextId(), user);
    }

    public Optional<User> getUser(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.of(users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteUser(Long userId) {
        users.remove(userId);
    }

    public void updateUser(User user) {
        users.remove(user.getId());
        users.put(user.getId(), user);
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
}
