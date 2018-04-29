package com.photos.api.controllers;

import com.photos.api.models.Photo;
import com.photos.api.services.PhotoService;
import com.photos.api.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RateService rateService;

    /**
     *
     * @return
     */
    @GetMapping
    public List<Photo> getAll() {
        return photoService.getAll();
    }

    /**
     *
     * @return
     */
    @GetMapping("/public")
    public ResponseEntity getPublic() {

        List<Photo> photos = photoService.getPublic();
        List<RatedPhoto> ratedPhotos = new ArrayList<>();
        for (Photo photo : photos) {
            ratedPhotos.add(new RatedPhoto(photo, rateService.getPhotoRate(photo)));
        }
        ratedPhotos.sort((o1, o2) -> o2.getRate() - o1.getRate());
        return ResponseEntity.ok().body(ratedPhotos);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Photo> getOne(@PathVariable(value = "id") final Long id) {
        Photo photo = photoService.getOne(id);
        byte rate = rateService.getPhotoRate(photo);
        return ResponseEntity.ok().body(photo);
    }

    /**
     *
     * @param photo
     * @return
     */
    @PostMapping
    public List<Photo> addPhoto(@RequestBody final Photo photo) {
        return photoService.addPhoto(photo);
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
    private class RatedPhoto {

        private Photo photo;
        private byte rate;

        public RatedPhoto(Photo photo, byte rate) {
            this.photo = photo;
            this.rate = rate;
        }

        public Photo getPhoto() {
            return photo;
        }

        public void setPhoto(Photo photo) {
            this.photo = photo;
        }

        public byte getRate() {
            return rate;
        }

        public void setRate(byte rate) {
            this.rate = rate;
        }
    }
}
