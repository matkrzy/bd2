package com.photos.api.controllers;

import com.photos.api.models.Share;
import com.photos.api.models.repositories.ShareRepository;
import com.photos.api.services.ShareService;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Micha Królewski on 2018-05-05.
 * @version 1.0
 */

@RestController
@RequestMapping("/shares")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @Autowired
    private ShareRepository shareRepository;

    @PostMapping
    public ResponseEntity addShare(@RequestBody final Share share){
        return shareService.addShare(share) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public List<Share> getall(){
        return shareRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteShare(@PathVariable final Long id){
        return shareService.deleteShare(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
