package com.photos.api.controllers;

import com.photos.api.services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Micha Królewski on 2018-04-13.
 * @version 1.0
 */

@RestController
public class ViewController {

    @Autowired
    private ViewService viewService;

}
