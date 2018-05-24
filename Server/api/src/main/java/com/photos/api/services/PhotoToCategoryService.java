package com.photos.api.services;

import com.photos.api.models.Category;
import com.photos.api.models.PhotoToCategory;
import com.photos.api.models.User;
import com.photos.api.models.repositories.CategoryRepository;
import com.photos.api.models.repositories.PhotoToCategoryRepository;
import com.photos.api.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepository userRepository;


    public Category getPhotoCategory(Long photo) {
        PhotoToCategory ptc = PTCRepository.findByPhoto(photo);
        return ptc != null ? categoryRepository.findByCategoryID(ptc.getCategory()) : null;
    }

    public boolean setCategory(PhotoToCategory ptc) {

        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);
        if (PTCRepository.findByPhoto(ptc.getPhoto()) != null) {
            return false;
        }
        if (categoryRepository.findByCategoryIDAndUser(ptc.getCategory(), user.getUserID()) == null) {
            return false;
        }
        PTCRepository.save(ptc);

        return true;
    }

    public boolean setNewCategory(Long photoId, Long categoryId) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        PhotoToCategory ptc = PTCRepository.findByPhoto(photoId);
        if (ptc == null) {
            return false;
        }
        if (categoryRepository.findByCategoryIDAndUser(categoryId, user.getUserID()) == null) {
            return false;
        }
        ptc.setCategory(categoryId);
        PTCRepository.save(ptc);
        return true;
    }

    public boolean deleteCategory(Long photoId) {

        PhotoToCategory ptc = PTCRepository.findByPhoto(photoId);
        if (ptc == null) {
            return false;
        }
        PTCRepository.delete(ptc);
        return true;
    }
}
