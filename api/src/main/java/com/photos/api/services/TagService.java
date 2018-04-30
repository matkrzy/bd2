package com.photos.api.services;

import com.photos.api.models.Photo;
import com.photos.api.models.Tag;
import com.photos.api.projections.PTag;
import com.photos.api.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<PTag> getAll() {
        List<Tag> list = tagRepository.findAll();
        List<PTag> tags = new ArrayList<>();

        for (Tag tag : list) {
            tags.add(new PTag(tag.getName()));
        }
        return tags;
    }

    public List<PTag> getAllForPhoto(final Photo photo) {
        List<Tag> list = tagRepository.getAllByPhoto(photo);
        List<PTag> tags = new ArrayList<>();

        for (Tag tag : list) {
            tags.add(new PTag(tag.getName()));
        }
        return tags;
    }
}
