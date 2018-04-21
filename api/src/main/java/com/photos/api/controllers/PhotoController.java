package com.photos.api.controllers;

import com.photos.api.models.Photo;
import com.photos.api.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version x
 */

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> getAll() {
        return photoService.getAll();
    }

    @GetMapping("/public")
    public List<Photo> getPublic() {
        return photoService.getPublic();
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
