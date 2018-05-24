package com.photos.api.controllers;

import com.photos.api.models.PhotoToCategory;
import com.photos.api.services.PhotoToCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Micha Królewski on 2018-05-22.
 * @version x
 */

@RestController
@RequestMapping("/category")
public class PTCController {

    @Autowired
    private PhotoToCategoryService service;

    @PostMapping
    public ResponseEntity setCategory(@RequestBody final PhotoToCategory ptc){
        return service.setCategory(ptc) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{photoId}/{categoryId}")
    public ResponseEntity setNewCategory(@PathVariable final Long photoId, @PathVariable final Long categoryId){
        return service.setNewCategory(photoId,categoryId) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity setNewCategory(@PathVariable final Long photoId){
        return service.deleteCategory(photoId) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
