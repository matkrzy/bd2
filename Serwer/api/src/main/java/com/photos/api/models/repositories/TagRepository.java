package com.photos.api.models.repositories;

import com.photos.api.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Component
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByPhoto(Long photo);

    List<Tag> findAllByNameLike(String name);

    List<Tag> findAllByUser(Long user);

    List<Tag> findAllByNameLikeAndUser(String name, Long user);

    Tag findByTagIDAndUser(Long id, Long user);

    void deleteAllByUser(Long userID);

    void deleteAllByPhoto(Long photo);

    Tag findByPhotoAndName(Long photo, String name);
}
