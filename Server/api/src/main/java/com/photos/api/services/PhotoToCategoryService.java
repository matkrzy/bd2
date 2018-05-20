package com.photos.api.services;

import com.photos.api.models.Category;
import com.photos.api.models.PhotoToCategory;
import com.photos.api.models.repositories.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;


    public Category getPhotoCategory(Long photo) {
        PhotoToCategory ptc = PTCRepository.findByPhoto(photo);
        Category category = categoryRepository.findByCategoryID(ptc.getCategory());
        return category;
    }
}
