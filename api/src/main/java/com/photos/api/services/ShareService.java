package com.photos.api.services;

import com.photos.api.repositories.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Service
public class ShareService {

    @Autowired
    private ShareRepository shareRepository;
}
