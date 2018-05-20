package com.photos.api.services;

import com.photos.api.models.Photo;
import com.photos.api.models.Rate;
import com.photos.api.models.User;
import com.photos.api.models.enums.PhotoState;
import com.photos.api.models.repositories.PhotoRepository;
import com.photos.api.models.repositories.RateRepository;
import com.photos.api.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Service
public class RateService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RateRepository rateRepository;

    /**
     * Pobiera wszystkie oceny danego zdjecia z bazy oraz wylicza srednia
     *
     * @param
     * @return {srednia ocena zdjecia}
     */
    public byte getPhotoRate(final Long photoid) {
        byte rate = 0;
        List<Rate> rates = rateRepository.findAllByPhoto(photoid);

        if (rates.size() != 0) {
            for (Rate r : rates) {
                rate += r.getRate();
            }
            rate /= rates.size();
        }
        return rate;
    }

    public boolean addRate(Rate rate) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        Rate check = rateRepository.findByPhotoAndUser(rate.getPhoto(), user.getUserID());
        if (check != null) {
            return false;
        }

        Photo photo = photoRepository.findByPhotoIDAndPhotoState(rate.getPhoto(), PhotoState.ACTIVE);
        if (photo == null) {
            return false;
        }

        try {
            rate.setUser(user.getUserID());
            rate.setDate(new Timestamp(System.currentTimeMillis()));
            rateRepository.save(rate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteRate(Long photoId) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        Rate rate = rateRepository.findByPhotoAndUser(photoId, user.getUserID());
        if (rate == null) {
            return false;
        }
        try {
            rateRepository.delete(rate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean editRate(Long photoId, byte rate) {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        Rate check = rateRepository.findByPhotoAndUser(photoId, user.getUserID());
        if (check == null) {
            return false;
        }
        try {
            check.setRate(rate);
            check.setDate(new Timestamp(System.currentTimeMillis()));
            rateRepository.save(check);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Rate> getAll() {
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        return rateRepository.findAllByUser(user.getUserID());
    }
}

