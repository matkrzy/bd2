package com.photos.api.models.repositories;

import com.photos.api.models.enums.ShareState;
import com.photos.api.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */

@Component
public interface PhotoRepository extends JpaRepository<Photo,Long> {
    List<Photo> findAllByUserEmail(String email);
    List<Photo> findAllByShareState(ShareState ss);
    Photo findByName(String filename);
    Photo findByPhotoID(Long id);
}
