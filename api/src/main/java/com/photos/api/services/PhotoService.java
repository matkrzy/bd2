package com.photos.api.services;

import com.photos.api.enums.ShareState;
import com.photos.api.models.Photo;
import com.photos.api.models.Share;
import com.photos.api.models.User;
import com.photos.api.projections.PPhoto;
import com.photos.api.projections.PTag;
import com.photos.api.repositories.PhotoRepository;
import com.photos.api.repositories.ShareRepository;
import com.photos.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    public List<PPhoto> getAll() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        List<Photo> photos = photoRepository.findAllByUserEmail(email);

        return createList(photoRepository.findAllByUserEmail(email));
    }

    /**
     * returning all public photos
     *
     * @return
     */
    public List<Photo> getPublic() {
        return photoRepository.findAllByShareState(ShareState.PUBLIC);
    }


    public List<Photo> getShared() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findFirstByEmail(email);
        List<Photo> photos = new ArrayList<>();

        List<Share> shares = shareRepository.findAllByUser(user);

        for (Share share : shares) {
            photos.add(photoRepository.findByPhotoID(share.getPhoto().getPhotoID()));
        }
        return photos;
    }

    /**
     * @param id
     * @return
     */
    public Photo getOne(final Long id) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Photo photo = photoRepository.findByPhotoID(id);

        return photo.getUser().getEmail().equals(email) ? photo : null;
    }

    /**
     * @param filename
     * @return
     */
    public Photo getOne(final String filename) {
        return photoRepository.findByName(filename);
    }


    /**
     * adding photo\     *
     *
     * @param photo
     * @return
     */
    public void addPhoto(final Photo photo) {
        // TODO: 2018-04-16 add exif here
        photoRepository.save(photo);
    }

    private List<PPhoto> createList(List<Photo> list) {

        List<PPhoto> photos = new ArrayList<>();
        for (Photo p : list) {
            List<PTag> tagList = tagService.getAllForPhoto(p);
            photos.add(new PPhoto(p, tagList));
        }

        return photos;
    }

}
