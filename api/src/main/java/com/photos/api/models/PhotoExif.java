package com.photos.api.models;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Micha Królewski on 2018-04-08.
 * @version 1.0
 */

@Entity
@Table(name = "photo_exif")
public class PhotoExif extends ResourceSupport {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long peID;

    public Long getPeID() {
        return peID;
    }

    public void setPeID(Long peID) {
        this.peID = peID;
    }
}
