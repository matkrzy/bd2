package com.photos.api.models;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Micha Królewski on 2018-04-08.
 * @version 1.0
 */


public class Rate extends ResourceSupport {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long rateID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @NotNull
    @Column(name = "datetime")
    private Timestamp datetime;

    @NotNull
    @Column(name = "rate")
    private Byte rate;

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

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Byte getRate() {
        return rate;
    }

    public void setRate(Byte rate) {
        this.rate = rate;
    }
}
