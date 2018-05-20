package com.photos.api.controllers;

import com.photos.api.models.Rate;
import com.photos.api.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Micha Królewski on 2018-05-19.
 * @version 1.0
 */

@RestController
@RequestMapping("/rates")
public class RateController {

    @Autowired
    private RateService rateService;

    @PostMapping
    public ResponseEntity addRate(@RequestBody final Rate rate) {
        return rateService.addRate(rate) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{photoId}/{rate}")
    public ResponseEntity editRate(@PathVariable final Long photoId, @PathVariable final byte rate) {
        return rateService.editRate(photoId, rate) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity deleteRate(@PathVariable final Long photoId) {
        return rateService.deleteRate(photoId) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Rate> rates = rateService.getAll();
        return rates.size() != 0 ?
                ResponseEntity.status(HttpStatus.OK).body(rates) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
