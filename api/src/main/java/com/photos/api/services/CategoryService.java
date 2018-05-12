package com.photos.api.services;

import com.photos.api.models.Category;
import com.photos.api.models.User;
import com.photos.api.models.repositories.CategoryRepository;
import com.photos.api.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;


    public Category getOne(final String name) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findFirstByEmail(email);
        Category category = categoryRepository.findByNameAndUser(name, user);
        return category;
    }

    public List<Category> getAll() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findFirstByEmail(email);
        List<Category> categories = categoryRepository.findAllByUser(user);
        return categories;
    }

    public boolean addCategory(Category category) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findFirstByEmail(email);

        if (user != null) {
            category.setUser(user);
            Category parent = null;
            if (category.getParentCategory() != null) {
                parent = categoryRepository.findByNameAndUser(category.getParentCategory().getName(), user);
                if (parent == null && category.getParentCategory().getName() != null) {
                    return false;
                }
            }


            category.setParentCategory(parent);
            categoryRepository.save(category);
        } else {
            return false;
        }
        return true;
    }
}
