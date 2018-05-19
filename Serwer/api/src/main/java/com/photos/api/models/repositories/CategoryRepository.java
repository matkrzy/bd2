package com.photos.api.models.repositories;

import com.photos.api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Category findByCategoryIDAndUser(final Long categoryID, final Long user);

    List<Category> findAllByParentCategoryAndUser(Long parent, Long user);

    void deleteAllByUser(Long user);

    Category findByCategoryID(Long category);

    List<Category> findAllByUser(Long userID);
}
