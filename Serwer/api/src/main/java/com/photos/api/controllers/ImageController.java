package com.photos.api.controllers;

import com.photos.api.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Micha Królewski on 2018-04-29.
 * @version x
 */

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{filename}")
    public ResponseEntity getImage(@PathVariable String filename) {

        try {
            Resource file = imageService.findImage(filename);
            return file != null ?
                    ResponseEntity.status(HttpStatus.OK).contentLength(file.contentLength()).contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(file.getInputStream())) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Couldn't find " + filename);
        }
    }

    @PostMapping
    public void addImage(@RequestParam("file") MultipartFile file) throws IOException {
        imageService.createImage(file);
    }
}
