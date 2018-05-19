package com.photos.api.services;

import com.photos.api.models.Photo;
import com.photos.api.models.Share;
import com.photos.api.models.User;
import com.photos.api.models.enums.PhotoState;
import com.photos.api.models.enums.ShareState;
import com.photos.api.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.photos.api.services.ImageService.UPLOAD_ROOT;

/**
 * @author Micha Królewski on 2018-04-14.
 * @version 1.0
 */

@Transactional
@Service
public class PhotoService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private PhotoToCategoryRepository ptcRepository;

    /**
     * returning all photos for logged user
     *
     * @return
     */
    public List<Photo> getAll() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);
        List<Photo> photos = photoRepository.findAllByUserAndPhotoState(user.getEmail(), PhotoState.ACTIVE);
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
    public Photo getPhoto(final Long id) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Photo photo = photoRepository.findByPhotoIDAndPhotoState(id, PhotoState.ACTIVE);

        return photo != null && photo.getUser().equals(email) ? photo : null;
    }

    public Photo getPhoto(final String name) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Photo photo = photoRepository.findByNameAndPhotoState(name, PhotoState.ACTIVE);

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

            User user = userRepository.findByEmail(email);
            photo.setUser(email);
            photo.setUserID(user.getUserID());
            photo.setUploadTime(new Timestamp(System.currentTimeMillis()));
            if (photo.getPhotoState() == null) {
                photo.setPhotoState(PhotoState.ACTIVE);
            }
            if (photo.getShareState() == null) {
                photo.setShareState(ShareState.PRIVATE);
            }

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
            ptcRepository.deleteAllByPhoto(check.getPhotoID());
            shareRepository.deleteAllByPhoto(check.getPhotoID());
            rateRepository.deleteAllByPhoto(check.getPhotoID());
            tagRepository.deleteAllByPhoto(check.getPhotoID());
            Files.deleteIfExists(Paths.get(UPLOAD_ROOT + "\\" + email + "\\", check.getName()));
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
