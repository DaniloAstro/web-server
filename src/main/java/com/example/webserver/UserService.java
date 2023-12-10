// UserService.java
package com.example.webserver;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void addUser(User user);
    void updateUser(Long id, User updatedUser);
    void deleteUser(Long id);

    Long getLastUsedId();
    void setLastUsedId(Long lastUsedId);

    List<User> getUsersByNameContaining(String name);
}