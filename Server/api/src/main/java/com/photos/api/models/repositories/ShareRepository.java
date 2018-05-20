package com.photos.api.models.repositories;

import com.photos.api.models.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Component
public interface ShareRepository extends JpaRepository<Share, Long> {
    List<Share> findAllByUser(Long user);

    Share findByPhotoAndUser(Long photo, Long user);

    Share findByShareIDAndOwner(Long id, Long owner);

    void deleteAllByUser(Long userID);

    void deleteAllByPhoto(Long photo);
}
