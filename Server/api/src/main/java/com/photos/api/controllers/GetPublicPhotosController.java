package com.photos.api.controllers;

/**
 * @author Micha Królewski on 2018-05-26.
 * @version x
 */

import com.photos.api.models.Photo;
import com.photos.api.models.ResponsePhoto;
import com.photos.api.models.Tag;
import com.photos.api.models.enums.ShareState;
import com.photos.api.services.PhotoService;
import com.photos.api.services.RateService;
import com.photos.api.services.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/photos/public")
public class GetPublicPhotosController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private RateService rateService;

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "Returns public photos which belongs to any of tags", response = ResponsePhoto.class)
    @GetMapping("/tags/any/{tags}")
    public ResponseEntity getPublicByTagsAny(@ApiParam(required = true, value = "id1,id2,...") @PathVariable List<Tag> tags) {

        List<ResponsePhoto> responsePhotos = convert(photoService.getByTagsAny(tags, ShareState.PUBLIC));
        if (responsePhotos == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        responsePhotos.sort((o1, o2) -> o2.getRate() - o1.getRate());
        return ResponseEntity.status(HttpStatus.OK).body(responsePhotos);
    }

    @ApiOperation(value = "Returns public photos which belongs to all of tags", response = ResponsePhoto.class)
    @GetMapping("/tags/all/{tags}")
    public ResponseEntity getPublicByTagsAll(@ApiParam(required = true, value = "id1,id2,...") @PathVariable List<Tag> tags) {

        List<ResponsePhoto> responsePhotos = convert(photoService.getByTagsAll(tags, ShareState.PUBLIC));
        if (responsePhotos == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        responsePhotos.sort((o1, o2) -> o2.getRate() - o1.getRate());
        return ResponseEntity.status(HttpStatus.OK).body(responsePhotos);
    }

    @ApiOperation(value = "Returns public photos", response = ResponsePhoto.class)
    @GetMapping
    public ResponseEntity getPublic() {

        List<ResponsePhoto> responsePhotos = convert(photoService.getPublic());
        if (responsePhotos == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        responsePhotos.sort((o1, o2) -> o2.getRate() - o1.getRate());
        return ResponseEntity.status(HttpStatus.OK).body(responsePhotos);
    }

    private List<ResponsePhoto> convert(List<Photo> photos) {

        List<ResponsePhoto> responsePhotos = new ArrayList<>();

        for (Photo photo : photos) {
            responsePhotos.add(
                    new ResponsePhoto(photo,
                            rateService.getPhotoRate(photo),
                            tagService.getPhotoTags(photo)));
        }
        return responsePhotos.size() == 0 ? null : responsePhotos;
    }
}
