package com.photos.api.models.repositories;

import com.photos.api.models.Share;
import com.photos.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Component
public interface ShareRepository extends JpaRepository<Share, Long> {
    List<Share> findAllByUser(final User user);
}
