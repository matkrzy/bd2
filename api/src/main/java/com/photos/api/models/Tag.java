package com.photos.api.models;


import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */


@Entity
@Table(name = "tag")
public class Tag {

    @Id @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long tagID;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @NotBlank
    @Column(name = "name")
    private String name;

    public Long getTagID() {
        return tagID;
    }

    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
