package com.example.springboot05.controller;

import com.example.springboot05.entity.User;
import com.example.springboot05.responsitory.UserResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired //Denpendency Injextion DI
    UserResponsitory userResponsitory; //CRUD

    @RequestMapping("/")
    public String index(Model model) {
        List<User> users = (List<User>) userResponsitory.findAll();

        //Step 3 return data to View
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user) {
        userResponsitory.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long userId, Model model) {
        Optional<User> userEdit = userResponsitory.findById(userId);
        userEdit.ifPresent(user -> model.addAttribute("user", user));return "editUser";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Long userId, Model model) {
        userResponsitory.deleteById(userId);
        return "redirect:/";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchUserByName(@RequestParam("name") String name, Model model) {
        List<User> users = userResponsitory.findUserByName(name);
        model.addAttribute("users", users);
        return "searchUser";
    }
}
