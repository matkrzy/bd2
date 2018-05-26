package com.photos.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Micha Królewski on 2018-04-08.
 * @version 1.0
 */

@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long rateID;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    @NotNull
    @OneToOne
    @JoinColumn(name = "photo")
    private Photo photo;

    @NotNull
    @Column(name = "date")
    private Timestamp date;

    public Long getRateID() {
        return rateID;
    }

    public void setRateID(Long rateID) {
        this.rateID = rateID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
