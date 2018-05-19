package com.photos.api.models.repositories;

import com.photos.api.models.Category;
import com.photos.api.models.PhotoToCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Micha Królewski on 2018-05-13.
 * @version x
 */


public interface PhotoToCategoryRepository extends JpaRepository<PhotoToCategory, Long> {

    PhotoToCategory findByPhoto(final Long photo);

    void deleteAllByCategory(Long category);

    void deleteAllByPhoto(Long photoID);
}
