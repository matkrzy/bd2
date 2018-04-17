package com.photos.api.controllers;

import com.photos.api.configs.Version;
import com.photos.api.enums.Role;
import com.photos.api.models.User;
import com.photos.api.repositories.UserRepository;
import com.photos.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */

@RestController
@RequestMapping(Version.version + "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable(value = "id") final Long id) {
        return userService.getOne(id);
    }

}
