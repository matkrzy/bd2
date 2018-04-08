package com.photos.api.models;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Micha Królewski on 2018-04-08.
 * @version 1.0
 */


public class PhotoToCategory extends ResourceSupport {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long ptcID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getPtcID() {
        return ptcID;
    }

    public void setPtcID(Long ptcID) {
        this.ptcID = ptcID;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
