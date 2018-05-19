package com.photos.api.services;

import com.photos.api.models.Photo;
import com.photos.api.models.Share;
import com.photos.api.models.User;
import com.photos.api.models.enums.PhotoState;
import com.photos.api.models.enums.ShareState;
import com.photos.api.models.repositories.PhotoRepository;
import com.photos.api.models.repositories.ShareRepository;
import com.photos.api.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    @Autowired
    private TagService tagService;

    @Autowired
    private ShareRepository shareRepository;

    /**
     * returning all photos for logged user
     *
     * @return
     */
    public List<Photo> getAll() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);
        List<Photo> photos = photoRepository.findAllByUserAndAndPhotoState(user.getEmail(), PhotoState.ACTIVE);
        return photos;
    }

    /**
     * returning all public photos
     *
     * @return
     */
    public List<Photo> getPublic() {
        return photoRepository.findAllByShareStateAndPhotoState(ShareState.PUBLIC, PhotoState.ACTIVE);
    }


    /**
     * @return
     */
    public List<Photo> getShared() {
        User user = userRepository.findByEmail(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        List<Photo> photos = new ArrayList<>();
        List<Share> shares = shareRepository.findAllByUser(user.getUserID());

        for (Share share : shares) {
            photos.add(photoRepository.findByPhotoIDAndPhotoState(share.getPhoto(), PhotoState.ACTIVE));
        }
        return photos;
    }

    /**
     * @param id
     * @return
     */
    public Photo getOne(final Long id) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Photo photo = photoRepository.findByPhotoIDAndPhotoState(id, PhotoState.ACTIVE);

        return photo != null && photo.getUser().equals(email) ? photo : null;
    }

    /**
     * @param photo
     * @return
     */
    public boolean addPhoto(final Photo photo) {

        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        try {

            Photo check = photoRepository.findByNameAndPhotoState(photo.getName(), PhotoState.ACTIVE);
            if (check != null) {
                return false;
            }

            photo.setUser(email);
            photo.setUploadTime(new Timestamp(System.currentTimeMillis()));
            // TODO: 2018-05-15 add exif
            photo.setExif(null);
            photoRepository.save(photo);
        } catch (Exception e) {
            return false;
        }
        return true;

    }


    public boolean deletePhoto(Long id) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Photo check = photoRepository.findByPhotoIDAndUser(id, email);

        if (check == null) {
            return false;
        }

        try {
            // TODO: 2018-05-19 delete image
            photoRepository.delete(check);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean editPhoto(Long id, Photo photo) {

        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Photo photoToUpdate = photoRepository.findByPhotoIDAndUser(id, email);

        if (photoToUpdate == null) {
            return false;
        }

        try {

            if (!photoToUpdate.getDescription().equals(photo.getDescription()) && photo.getDescription() != null) {
                photoToUpdate.setDescription(photo.getDescription());
            }
            if (!photoToUpdate.getPhotoState().equals(photo.getPhotoState()) && photo.getPhotoState() != null) {
                photoToUpdate.setPhotoState(photo.getPhotoState());
            }
            if (!photoToUpdate.getShareState().equals(photo.getShareState()) && photo.getShareState() != null) {
                photoToUpdate.setShareState(photo.getShareState());
            }

            photoRepository.save(photoToUpdate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
