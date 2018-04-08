package com.photos.api.models;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Micha Królewski on 2018-04-08.
 * @version 1.0
 */


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
