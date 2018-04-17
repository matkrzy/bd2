package com.photos.api.services;

import com.photos.api.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Micha Królewski on 2018-04-13.
 * @version 1.0
 */

@Service
public class ViewService {

    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
