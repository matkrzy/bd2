package com.photos.api.controllers;

import com.photos.api.models.Photo;
import com.photos.api.models.Tag;
import com.photos.api.projections.PPhoto;
import com.photos.api.projections.PTag;
import com.photos.api.services.PhotoService;
import com.photos.api.services.RateService;
import com.photos.api.services.TagService;
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

    @Autowired
    private TagService tagService;

    /**
     * @return
     */
    @GetMapping
    public List<PPhoto> getAll() {
        return photoService.getAll();
    }

    /**
     * @return
     */
    @GetMapping("/public")
    public ResponseEntity getPublic() {

        List<Photo> photos = photoService.getPublic();
        List<ResponsePhoto> responsePhotos = new ArrayList<>();

        for (Photo photo : photos) {
            List<PTag> list = tagService.getAllForPhoto(photo);
            responsePhotos.add(new ResponsePhoto(photo, rateService.getPhotoRate(photo), tagService.getAllForPhoto(photo)));
        }
        responsePhotos.sort((o1, o2) -> o2.getRate() - o1.getRate());
        return ResponseEntity.ok().body(responsePhotos);
    }

    /**
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
     * @param photo
     * @return
     */
    @PostMapping
    public void addPhoto(@RequestBody final Photo photo) {
        photoService.addPhoto(photo);
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
    private class ResponsePhoto {

        private Photo photo;
        private byte rate;
        private List<PTag> tags;

        public ResponsePhoto(Photo photo, byte rate, List<PTag> tags) {
            this.photo = photo;
            this.rate = rate;
            this.tags = tags;
        }

        public List<PTag> getTags() {
            return tags;
        }

        public void setTags(List<PTag> tags) {
            this.tags = tags;
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
