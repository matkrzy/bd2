package com.photos.api.services;

import com.photos.api.models.Category;
import com.photos.api.models.User;
import com.photos.api.models.enums.Role;
import com.photos.api.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.photos.api.services.ImageService.UPLOAD_ROOT;

/**
 * @author Micha Królewski on 2018-04-14.
 * @version 1.0
 */

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoToCategoryRepository PTCRepository;

    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private TagRepository tagRepository;

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

    public boolean addUser(final User user) throws IOException {
        User user1 = userRepository.findByEmail(user.getEmail());
        if (user1 == null) {
            if (user.getEmail() == null || user.getPassword() == null || user.getLastName() == null || user.getFirstName() == null) {
                return false;
            }

            user.setRole(Role.USER);
            userRepository.save(user);
            Files.createDirectory(Paths.get(UPLOAD_ROOT + "/" + user.getEmail()));

        } else {
            return false;
        }
        return true;
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

    public boolean deleteUser() {
        User user = userRepository.findByEmail(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        try {

            List<Category> categories = categoryRepository.findAllByUser(user.getUserID());
            for (Category category : categories) {
                PTCRepository.deleteAllByCategory(category.getCategoryID());
            }
            categoryRepository.deleteAllByUser(user.getUserID());

            shareRepository.deleteAllByUser(user.getUserID());
            tagRepository.deleteAllByUser(user.getUserID());
            photoRepository.deleteAllByUserID(user.getUserID());

            // TODO: 2018-05-19 delete folder with images
            userRepository.delete(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
