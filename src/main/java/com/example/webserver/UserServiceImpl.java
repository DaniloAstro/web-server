// UserServiceImpl.java
package com.example.webserver;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    List<User> users = new ArrayList<>(List.of(
            User.builder()
                    .id(1L)
                    .name("Jackie Wells")
                    .email("JackieWells@gmail.com")
                    .age(30)
                    .country("Night City")
                    .build(),
            User.builder()
                    .id(2L)
                    .name("Adam Smasher")
                    .email("Smasher@gmail.com")
                    .age(60)
                    .country("New York City, USA")
                    .build(),
            User.builder()
                    .id(3L)
                    .name("Johnny Silverhand")
                    .email("Silverhand@gmail.com")
                    .age(34)
                    .country("College Station, Texas")
                    .build(),
            User.builder()
                    .id(4L)
                    .name("Judy Alvarez")
                    .email("JudyAlvarez@gmail.com")
                    .age(25)
                    .country("Laguna Bend, Badlands")
                    .build()
    ));

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setId(updatedUser.getId());
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAge(updatedUser.getAge());
            existingUser.setCountry(updatedUser.getCountry());
        }
    }

    @Override
    public void deleteUser(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public Long getLastUsedId() {
        return null;
    }

    @Override
    public void setLastUsedId(Long lastUsedId) {

    }

    @Override
    public List<User> getUsersByNameContaining(String name) {
        if (name == null || name.isEmpty()) {
            // If the filter is empty, return all users
            return getAllUsers();
        } else {
            // Filter users by name
            return users.stream()
                    .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }
}