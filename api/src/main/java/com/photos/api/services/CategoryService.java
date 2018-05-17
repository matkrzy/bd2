package com.photos.api.services;

import com.photos.api.models.Category;
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

    /**
     * @param name
     * @return
     */
    public Category getOne(final String name) {
        String user = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return categoryRepository.findFirstByNameAndUser(name, user);
    }

    /**
     * @param parent
     * @param name
     * @return
     */
    public Category getOne(final String parent, final String name) {
        String user = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return categoryRepository.findByParentCategoryAndNameAndUser(parent, name, user);
    }

    /**
     * @return
     */
    public List<Category> getAll() {
        String user = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        List<Category> categories = categoryRepository.findAllByUser(user);
        return categories;
    }

    /**
     * @param category
     * @return
     */
    public boolean addCategory(Category category) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if (email == null) {
            return false;
        }

        if (category.getParentCategory() != null) {

            Category parentCat = categoryRepository.findByCategoryIDAndUser(Long.parseLong(category.getParentCategory()), email);

            if (parentCat != null) {

                Category check = categoryRepository.findByParentCategoryAndNameAndUser(parentCat.getName(), category.getName(), email);

                if (check != null) {
                    return false;
                }


                category.setParentCategory(parentCat.getName());
            } else {
                return false;
            }
        }

        category.setUser(email);
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            return false;
        }

        return true;

    }
}
