package com.photos.api.controllers;

import com.photos.api.models.Photo;
import com.photos.api.models.Rate;
import com.photos.api.services.RateService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Creates new rate")
    @PostMapping
    public ResponseEntity addRate(@RequestBody final Rate rate) {
        return rateService.addRate(rate) ?
                ResponseEntity.status(HttpStatus.CREATED).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ApiOperation(value = "Removes rate")
    @DeleteMapping("/{photo}")
    public ResponseEntity deleteRate(@PathVariable final Photo photo) {
        return rateService.deleteRate(photo) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
