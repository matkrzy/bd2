package com.photos.api.services;

import com.photos.api.controllers.UserController;
import com.photos.api.models.User;
import com.photos.api.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public void addUser(final User user) {
        userRepository.save(user);
    }

    public void updateUser(final User user) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User userToUpdate = userRepository.findFirstByEmail(email);
        if(user.getFirstName() != null)
             userToUpdate.setFirstName(user.getFirstName());

        if(user.getLastName() != null)
            userToUpdate.setLastName(user.getLastName());

        if(user.getPassword() != null)
            userToUpdate.setPassword(user.getPassword());

        userRepository.save(userToUpdate);
    }
}
