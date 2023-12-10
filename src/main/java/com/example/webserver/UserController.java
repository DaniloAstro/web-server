// UserController.java
package com.example.webserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showAllUsers(@RequestParam(name = "nameFilter", required = false) String nameFilter, Model model) {
        List<User> users;
        if (nameFilter != null && !nameFilter.isEmpty()) {

            users = userService.getUsersByNameContaining(nameFilter);
        } else {

            users = userService.getAllUsers();
        }

        model.addAttribute("users", users);
        model.addAttribute("nameFilter", nameFilter);

        return "all-users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("newUser", new User());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User newUser) {
        Long generatedId = generateUniqueId();
        newUser.setId(generatedId);
        userService.addUser(newUser);
        return "redirect:/users/";
    }

    private Long generateUniqueId() {

        Long lastUsedId = userService.getLastUsedId();
        if (lastUsedId == null) {
            lastUsedId = 4L;
        }
        Long newId = lastUsedId + 1;
        userService.setLastUsedId(newId);
        return newId;
    }

    @GetMapping("/update/{id}")
    public String showUpdateUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User updatedUser) {
        userService.updateUser(id, updatedUser);
        return "redirect:/users/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users/";
    }
}