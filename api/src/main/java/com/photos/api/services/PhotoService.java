package com.photos.api.services;

import com.photos.api.models.Photo;
import com.photos.api.models.User;
import com.photos.api.repositories.PhotoRepository;
import com.photos.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    public List<Photo> getAll() {
        User user = userRepository.findUserByUserID(1l);

        ////Photo photo = new Photo("",user,"",new Timestamp(System.currentTimeMillis()),"",ShareState.PUBLIC,PhotoState.ACTIVE);
        // photoRepository.save(photo);

//        User user = userRepository.findUserByUserID(1l);
        List<Photo> photoList = photoRepository.findAll();
//        photoList.get(1).add(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getUserID()).withSelfRel());
        return photoList;
    }

    public Photo getOne(final Long id) {
        return photoRepository.findPhotoByPhotoID(id);
    }


    public List<Photo> addPhoto(final Photo photo) {
        photoRepository.save(photo);
        return photoRepository.findAll();
    }
}
