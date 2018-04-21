package com.photos.api.services;

import com.photos.api.enums.ShareState;
import com.photos.api.models.Photo;
import com.photos.api.repositories.PhotoRepository;
import com.photos.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-14.
 * @version 1.0
 */

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * returning all photos for logged user
     *
     * @return
     */
    public List<Photo> getAll() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return photoRepository.findAllByUserEmail(email);
    }

    /**
     * returning all public photos
     * @return
     */
    public List<Photo> getPublic() {
        return photoRepository.findAllByShareState(ShareState.PUBLIC);
    }

    /**
     *
     * @param id
     * @return
     */
    public Photo getOne(final Long id) {
        return photoRepository.findPhotoByPhotoID(id);
    }

    /**
     * adding photo
     * todo change this
     * @param photo
     * @return
     */
    public List<Photo> addPhoto(final Photo photo) {
        // TODO: 2018-04-16 add exif here
        photoRepository.save(photo);
        return photoRepository.findAll();
    }


}
