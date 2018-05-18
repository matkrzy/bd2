package com.photos.api.services;

import com.photos.api.models.PhotoToCategory;
import com.photos.api.models.repositories.PhotoToCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Micha Królewski on 2018-05-12.
 * @version 1.0
 */

@Service
public class PhotoToCategoryService {

    @Autowired
    private PhotoToCategoryRepository PTCRepository;


    public String getPhotoCategory(Long photo) {
        PhotoToCategory category = PTCRepository.findByPhoto(photo);
        return category != null ? category.getCategory() : null;
    }
}
