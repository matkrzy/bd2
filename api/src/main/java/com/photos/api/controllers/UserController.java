package com.photos.api.controllers;

import com.photos.api.models.User;
import com.photos.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable(value = "id") final Long id) {
        User user = userRepository.findUserByUserID(id);
        user.add(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getUserID()).withSelfRel());
        return user;
    }
}
