package com.photos.api.controllers;

import com.photos.api.models.Tag;
import com.photos.api.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-29.
 * @version x
 */

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/public")
    public ResponseEntity getPublicTags() {
        List<Tag> tags = tagService.getPublicTags();
        return tags != null ?
                ResponseEntity.status(HttpStatus.OK).body(tags) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/public/{name}")
    public ResponseEntity getPublicTags(@PathVariable final String name) {
        List<Tag> tags = tagService.getPublicTags(name);
        return tags != null ?
                ResponseEntity.status(HttpStatus.OK).body(tags) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity getTags() {
        List<Tag> tags = tagService.getTags();
        return tags != null ?
                ResponseEntity.status(HttpStatus.OK).body(tags) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{name}")
    public ResponseEntity getTags(@PathVariable final String name) {
        List<Tag> tags = tagService.getTags(name);
        return tags != null ?
                ResponseEntity.status(HttpStatus.OK).body(tags) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity addTag(@RequestBody final List<Tag> tags) {
        return tagService.addTag(tags) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTag(@PathVariable final Long id){
        return tagService.deleteTag(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}/{name}")
    public ResponseEntity editTag(@PathVariable final Long id,@PathVariable final String name){
        return tagService.editTag(id,name) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
