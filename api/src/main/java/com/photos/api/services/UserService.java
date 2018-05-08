package com.photos.api.services;

import com.photos.api.controllers.UserController;
import com.photos.api.models.User;
import com.photos.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-14.
 * @version 1.0
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getOne(final Long id) {
        User user = userRepository.findUserByUserID(id);
        user.add(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getUserID()).withSelfRel());
        return user;
    }

    public void addUser(final User user){
        userRepository.save(user);
    }
}
