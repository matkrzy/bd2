package com.photos.api.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */


@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long tagID;

    @NotNull
    @Column(name = "photo_id")
    private Long photo;

    @NotNull
    @Column(name = "user_id")
    private Long user;

    @NotNull
    @Column(name = "name")
    private String name;

    public Tag() {
    }

    public Tag(@NotNull Long photo, @NotNull Long user, @NotNull String name) {

        this.photo = photo;
        this.user = user;
        this.name = name;
    }

    public Long getTagID() {
        return tagID;
    }

    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }

    public Long getPhoto() {
        return photo;
    }

    public void setPhoto(Long photo) {
        this.photo = photo;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
