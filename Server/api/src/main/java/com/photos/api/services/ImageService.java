package com.photos.api.services;

import com.photos.api.models.Photo;
import com.photos.api.models.enums.PhotoState;
import com.photos.api.models.enums.ShareState;
import com.photos.api.models.repositories.PhotoRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Micha Królewski on 2018-04-26.
 * @version 1.0
 */

@Service
public class ImageService {

    public static String UPLOAD_ROOT = "C:\\Users\\MICHAL\\Documents\\bd2\\Server\\imageStore";
    private final ResourceLoader resourceLoader;
    private final PhotoRepository photoRepository;

    public ImageService(ResourceLoader resourceLoader, PhotoRepository photoRepository) {
        this.resourceLoader = resourceLoader;
        this.photoRepository = photoRepository;
    }


    public Resource findImage(Long id) {
        Photo photo = photoRepository.findByPhotoIDAndPhotoStateAndShareState(id, PhotoState.ACTIVE, ShareState.PUBLIC);

        if (photo == null) {
            return null;
        }
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + "\\" + photo.getPath());
    }

    public boolean createImage(MultipartFile file) {

        if (!file.isEmpty()) {
            try {

                String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
                String name = file.getOriginalFilename();
                Photo photo = photoRepository.findByNameAndUserAndPhotoState(name, email, PhotoState.ACTIVE);
                photo.setPath(/*UPLOAD_ROOT + "\\" +*/ email + "\\" + file.getOriginalFilename());
                photoRepository.save(photo);

                Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT + "\\" + email + "\\", file.getOriginalFilename()));
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }


    public void deleteImage(String filename) throws IOException {

        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Files.deleteIfExists(Paths.get(UPLOAD_ROOT + "\\" + email + "\\", filename));
    }
}
