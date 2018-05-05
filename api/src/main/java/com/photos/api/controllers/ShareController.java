package com.photos.api.controllers;

import com.photos.api.models.Photo;
import com.photos.api.models.Share;
import com.photos.api.repositories.ShareRepository;
import com.photos.api.services.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Micha Królewski on 2018-05-05.
 * @version 1.0
 */

@RestController
@RequestMapping("/shares")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @PostMapping
    public void addShare(@RequestBody final Share share){
        shareService.addShare(share);
    }
}
