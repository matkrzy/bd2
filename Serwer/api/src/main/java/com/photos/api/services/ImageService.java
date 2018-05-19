package com.photos.api.services;

import com.photos.api.models.Photo;
import com.photos.api.models.enums.PhotoState;
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

    public static String UPLOAD_ROOT = "C:\\Users\\MICHAL\\Desktop\\photosAPI";
    private final ResourceLoader resourceLoader;
    private final PhotoRepository photoRepository;

    public ImageService(ResourceLoader resourceLoader, PhotoRepository photoRepository) {
        this.resourceLoader = resourceLoader;
        this.photoRepository = photoRepository;
    }


    public Resource findImage(String filename) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Photo photo = photoRepository.findByNameAndPhotoState(filename,PhotoState.ACTIVE);

        // return photo != null ? resourceLoader.getResource("file:" + UPLOAD_ROOT + " " + filename) : null;
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + "\\" + filename);
    }

    public boolean createImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            Photo photo = photoRepository.findByNameAndPhotoState(name,PhotoState.ACTIVE);
            photo.setPath(UPLOAD_ROOT + "\\" + file.getOriginalFilename());
            photoRepository.save(photo);

            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
        }
        return true;
    }


    public void deleteImage(String filename) throws IOException {
        //final Photo img = photoRepository.findByName(filename);
        //photoRepository.delete(img);
        Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
    }
}
