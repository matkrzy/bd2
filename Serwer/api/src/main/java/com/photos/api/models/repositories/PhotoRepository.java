package com.photos.api.models.repositories;

import com.photos.api.models.Photo;
import com.photos.api.models.enums.PhotoState;
import com.photos.api.models.enums.ShareState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */

@Component
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findAllByUserAndAndPhotoState(String email, PhotoState photoState);

    List<Photo> findAllByShareStateAndPhotoState(ShareState ss, PhotoState photoState);

    Photo findByNameAndPhotoState(String name, PhotoState photoState);

    Photo findByPhotoIDAndPhotoState(Long id, PhotoState photoState);

    Photo findByPhotoIDAndUserAndPhotoState(Long id, String user, PhotoState photoState);

    Photo findByNameAndUserAndPhotoState(String name, String user, PhotoState photoState);

    void deleteAllByUserid(Long userID);

    Photo findByPhotoIDAndUser(Long id, String user);
}
