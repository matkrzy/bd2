package com.photos.api.services;

import com.photos.api.models.Photo;
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

    private static String UPLOAD_ROOT = "C:\\Users\\MICHAL\\Desktop\\photosAPI";
    private final ResourceLoader resourceLoader;
    private final PhotoRepository photoRepository;

    public ImageService(ResourceLoader resourceLoader, PhotoRepository photoRepository) {
        this.resourceLoader = resourceLoader;
        this.photoRepository = photoRepository;
    }


    public Resource findImage(String filename) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Photo photo = photoRepository.findByName(filename);
        if (photo == null) return null;
        return photo.getUser().getEmail().equals(email) ? resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + filename) : null;
    }

    public void createImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
        }
    }


    public void deleteImage(String filename) throws IOException {
        //final Photo img = photoRepository.findByName(filename);
        //photoRepository.delete(img);
        Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
    }
}
