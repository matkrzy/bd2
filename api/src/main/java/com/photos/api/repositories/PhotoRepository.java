package com.photos.api.repositories;

import com.photos.api.enums.ShareState;
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
    Photo findPhotoByPhotoID(Long id);
    List<Photo> findAllByUserEmail(String email);
    List<Photo> findAllByShareState(ShareState ss);
}
