package com.photos.api.controllers;

import com.photos.api.enums.PhotoState;
import com.photos.api.enums.ShareState;
import com.photos.api.models.Photo;
import com.photos.api.models.User;
import com.photos.api.repositories.PhotoRepository;
import com.photos.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version x
 */

@RestController
@RequestMapping("/api/v1/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<Photo> getAll() {
        User user = userRepository.findUserByUserID(1l);

        ////Photo photo = new Photo("",user,"",new Timestamp(System.currentTimeMillis()),"",ShareState.PUBLIC,PhotoState.ACTIVE);
       // photoRepository.save(photo);

//        User user = userRepository.findUserByUserID(1l);
        List<Photo> photoList = photoRepository.findAll();
//        photoList.get(1).add(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getUserID()).withSelfRel());
        return photoList;
    }

    @GetMapping("/{id}")
    public Photo getOne(@PathVariable(value = "id") final Long id) {
        return photoRepository.findPhotoByPhotoID(id);
    }

    @PostMapping("/add")
    public List<Photo> addPhoto(@RequestBody final Photo photo) {
        photoRepository.save(photo);
        return photoRepository.findAll();
    }


}
