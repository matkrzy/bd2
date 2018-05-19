package com.photos.api.controllers;

import com.photos.api.models.Photo;
import com.photos.api.models.ResponsePhoto;
import com.photos.api.services.PhotoService;
import com.photos.api.services.PhotoToCategoryService;
import com.photos.api.services.RateService;
import com.photos.api.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    @Autowired
    private PhotoToCategoryService PTCService;

    @Autowired
    private RateService rateService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity getAll() {
        List<ResponsePhoto> responsePhotos = convert(photoService.getAll());

        if (responsePhotos == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        responsePhotos.sort((o1, o2) -> o2.getUploadTime().compareTo(o1.getUploadTime()));
        return ResponseEntity.status(HttpStatus.OK).body(responsePhotos);
    }

    @GetMapping("/public")
    public ResponseEntity getPublic() {

        List<ResponsePhoto> responsePhotos = convert(photoService.getPublic());
        if (responsePhotos == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        responsePhotos.sort((o1, o2) -> o2.getRate() - o1.getRate());
        return ResponseEntity.status(HttpStatus.OK).body(responsePhotos);
    }

    @GetMapping("/shared")
    public ResponseEntity getShared() {

        List<ResponsePhoto> responsePhotos = convert(photoService.getShared());
        if (responsePhotos == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        responsePhotos.sort((o1, o2) -> o2.getUploadTime().compareTo(o1.getUploadTime()));
        return ResponseEntity.status(HttpStatus.OK).body(responsePhotos);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable(value = "id") final Long id) {
        Photo photo = photoService.getOne(id);
        if (photo != null) {
            ResponsePhoto p = new ResponsePhoto(photo,
                    rateService.getPhotoRate(photo.getPhotoID()),
                    tagService.getPhotoTags(photo.getPhotoID()),
                    PTCService.getPhotoCategory(photo.getPhotoID()));
            return ResponseEntity.status(HttpStatus.OK).body(p);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PostMapping
    public ResponseEntity addPhoto(@RequestBody final Photo photo) {
        return photoService.addPhoto(photo) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePhoto(@PathVariable final Long id) {
        return photoService.deletePhoto(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity editPhoto(@PathVariable final Long id, @RequestBody final Photo photo) {
        return photoService.editPhoto(id,photo) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }





    // TODO: 2018-04-29 delete this!
    @GetMapping("/index")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    /**
     * private class RaterPhoto
     * contain photo and its average rate
     */

    private List<ResponsePhoto> convert(List<Photo> photos) {

        List<ResponsePhoto> responsePhotos = new ArrayList<>();

        for (Photo photo : photos) {
            responsePhotos.add(new ResponsePhoto(
                    photo,
                    rateService.getPhotoRate(photo.getPhotoID()),
                    tagService.getPhotoTags(photo.getPhotoID()),
                    PTCService.getPhotoCategory(photo.getPhotoID())));
        }
        return responsePhotos.size() == 0 ? null : responsePhotos;
    }

}
