package com.photos.api.services;

import com.photos.api.models.Category;
import com.photos.api.models.Photo;
import com.photos.api.models.PhotoToCategory;
import com.photos.api.models.User;
import com.photos.api.models.repositories.CategoryRepository;
import com.photos.api.models.repositories.PhotoToCategoryRepository;
import com.photos.api.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


//    public List<Category> getPhotoCategory(Photo photo) {
//        List<PhotoToCategory> ptcs = PTCRepository.findAllByPhoto(photo);
//        List<Category> categories = new ArrayList<>();
//        for (PhotoToCategory ptc : ptcs) {
//            categories.add(ptc.getCategory());
//        }
//        return categories;
//    }

    public boolean setCategory(PhotoToCategory ptc) {

        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);
        if (PTCRepository.findByPhotoAndCategory(ptc.getPhoto(), ptc.getCategory()) != null) {
            return false;
        }
        if (categoryRepository.findByCategoryIDAndUser(ptc.getCategory().getCategoryID(), user) == null) {
            return false;
        }
        PTCRepository.save(ptc);

        return true;
    }

    public boolean setNewCategory(PhotoToCategory ptc, Category category) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        PhotoToCategory check = PTCRepository.findByPhotoAndCategory(ptc.getPhoto(), ptc.getCategory());
        if (check == null) {
            return false;
        }
        if (categoryRepository.findByCategoryIDAndUser(category.getCategoryID(), user) == null) {
            return false;
        }
        check.setCategory(category);
        PTCRepository.save(check);
        return true;
    }

    public boolean deleteCategory(PhotoToCategory ptcc) {

        PhotoToCategory ptc = PTCRepository.findByPhotoAndCategory(ptcc.getPhoto(), ptcc.getCategory());
        if (ptc == null) {
            return false;
        }
        PTCRepository.delete(ptc);
        return true;
    }
}
