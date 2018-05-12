package com.photos.api.models.repositories;

import com.photos.api.models.Category;
import com.photos.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameAndUser(final String name, final User user);

    List<Category> findAllByUser(final User user);
}
