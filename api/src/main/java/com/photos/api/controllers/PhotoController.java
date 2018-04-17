package com.photos.api.controllers;

import com.photos.api.configs.Version;
import com.photos.api.enums.PhotoState;
import com.photos.api.enums.ShareState;
import com.photos.api.models.Photo;
import com.photos.api.models.User;
import com.photos.api.repositories.PhotoRepository;
import com.photos.api.repositories.UserRepository;
import com.photos.api.services.PhotoService;
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
@RequestMapping(Version.version + "/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> getAll() {
        return photoService.getAll();
    }

    @GetMapping("/{id}")
    public Photo getOne(@PathVariable(value = "id") final Long id) {
        return photoService.getOne(id);
    }

    @PostMapping
    public List<Photo> addPhoto(@RequestBody final Photo photo) {
        return photoService.addPhoto(photo);
    }
}
