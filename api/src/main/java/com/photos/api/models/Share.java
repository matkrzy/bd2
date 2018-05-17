package com.photos.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Micha Królewski on 2018-04-08.
 * @version 1.0
 */

@Entity
@Table(name = "share")
public class Share {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long shareID;

    @NotNull
    @Column(name = "user_email")
    private String user;

    @NotNull
    @Column(name = "photo_id")
    private Long photo;

    public Long getShareID() {
        return shareID;
    }

    public void setShareID(Long shareID) {
        this.shareID = shareID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getPhoto() {
        return photo;
    }

    public void setPhoto(Long photo) {
        this.photo = photo;
    }
}


