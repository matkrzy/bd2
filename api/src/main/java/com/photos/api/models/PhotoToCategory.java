package com.photos.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Micha Królewski on 2018-04-08.
 * @version 1.0
 */

@Entity
@Table(name = "photo_to_category")
public class PhotoToCategory {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long ptcID;

    @NotNull
    @Column(name = "photo_id")
    private Long photo;

    @NotNull
    @Column(name = "category_name")
    private String category;

    public PhotoToCategory() {
    }

    public PhotoToCategory(@NotNull Long photo, @NotNull String category) {
        this.photo = photo;
        this.category = category;
    }

    public Long getPtcID() {
        return ptcID;
    }

    public void setPtcID(Long ptcID) {
        this.ptcID = ptcID;
    }

    public Long getPhoto() {
        return photo;
    }

    public void setPhoto(Long photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
