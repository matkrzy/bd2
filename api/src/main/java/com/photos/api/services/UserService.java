package com.photos.api.services;

import com.photos.api.models.User;
import com.photos.api.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User getOne(final String email) {
        String sessionEmail = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if (!email.equals(sessionEmail)) {
            return null;
        }

        User user = userRepository.findByEmail(email);
        return user;
    }

    public boolean addUser(final User user) {
        User user1 = userRepository.findByEmail(user.getEmail());
        if (user1 == null) {
            userRepository.save(user);
            // Files.createDirectory(UPLOAD_ROOT)
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUser(final User user) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User userToUpdate = userRepository.findByEmail(email);

        if (user.getFirstName() != null)
            userToUpdate.setFirstName(user.getFirstName());

        if (user.getLastName() != null)
            userToUpdate.setLastName(user.getLastName());

        if (user.getPassword() != null)
            userToUpdate.setPassword(user.getPassword());

        try {

            userRepository.save(userToUpdate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
