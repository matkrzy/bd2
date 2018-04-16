package com.photos.api.services;

import com.photos.api.controllers.UserController;
import com.photos.api.enums.Role;
import com.photos.api.models.User;
import com.photos.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

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

    public ModelAndView logInUserAndAdmin(User User) {

        ModelAndView modelAndView = new ModelAndView();

        // //User validation, checking e-maill and password in DB
        User validateUser = userRepository.findFirstByEmail(User.getEmail());

        if ((validateUser != null)) {
            if (User.getPassword().equals(validateUser.getPassword())) {
                if (validateUser.getRole() == Role.USER) {
                    modelAndView.setViewName("redirect:/");
                    return modelAndView;
                } else if (validateUser.getRole() == Role.ADMIN) {
                    modelAndView.setViewName("redirect:/");
                    return modelAndView;
                }
            } else {
                // TODO: 2018-04-14 wrong password message
            }
        }

        // TODO: 2018-04-14 no user found message
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
