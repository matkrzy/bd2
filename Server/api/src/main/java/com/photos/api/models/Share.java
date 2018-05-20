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
    @Column(name = "user_id")
    private Long user;

    @NotNull
    @Column(name = "photo_id")
    private Long photo;

    @NotNull
    @Column(name = "owner_id")
    private Long owner;

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Long getShareID() {
        return shareID;
    }

    public void setShareID(Long shareID) {
        this.shareID = shareID;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getPhoto() {
        return photo;
    }

    public void setPhoto(Long photo) {
        this.photo = photo;
    }
}


