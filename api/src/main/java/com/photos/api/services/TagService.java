package com.photos.api.services;

import com.photos.api.models.Photo;
import com.photos.api.models.Tag;
import com.photos.api.models.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;


    /**
     * Pobiera wszystkie tagi z bazy oraz tworzy z nich liste nazw
     *
     * @return {lista nazw tagow}
     */
    public String[] getAll() {
        List<Tag> list = tagRepository.findAll();
        String[] tags = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            tags[i] = list.get(i).getName();
        }
        return tags;
    }

    /**
     * Pobiera wszystkie tagi dla danego zdjecia oraz tworzy z nich liste nazw
     *
     * @param photo
     * @return {lista nazw tagow}
     */
    public String[] getAllForPhoto(final Photo photo) {
        List<Tag> list = tagRepository.getAllByPhoto(photo);
        String[] tags = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            tags[i] = list.get(i).getName();
        }
        return tags;
    }
}
