package com.photos.api.models.repositories;

import com.photos.api.models.Category;
import com.photos.api.models.Photo;
import com.photos.api.models.PhotoToCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Micha Królewski on 2018-05-13.
 * @version x
 */


public interface PhotoToCategoryRepository extends JpaRepository<PhotoToCategory, Long> {

    PhotoToCategory findByPhotoAndCategory(Photo photo, Category category);

    void deleteAllByCategory(Category category);

    void deleteAllByPhoto(Photo photoID);

    List<PhotoToCategory> findAllByPhoto(Photo photo);

    List<PhotoToCategory> findAllByCategory(Category category);

    List<PhotoToCategory> findAllByCategoryIn(List<Category> categories);

    List<PhotoToCategory> findAllByCategoryIsIn(List<Category> categories);
}
