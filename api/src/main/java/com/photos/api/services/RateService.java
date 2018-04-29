package com.photos.api.services;

import com.photos.api.models.Photo;
import com.photos.api.models.Rate;
import com.photos.api.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public byte getPhotoRate(final Photo photo) {
        byte rate = 0;
        List<Rate> rates = rateRepository.findAllByPhoto(photo);

        if (rates.size() != 0) {
            for (Rate r : rates) {
                rate += r.getRate();
            }
            rate /= rates.size();
        }
        return rate;
    }

}
