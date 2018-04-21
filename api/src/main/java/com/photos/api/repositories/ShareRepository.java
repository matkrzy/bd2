package com.photos.api.repositories;

import com.photos.api.models.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Component
public interface ShareRepository extends JpaRepository<Share, Long> {
}
