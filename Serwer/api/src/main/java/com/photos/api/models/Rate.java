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
    @Column(name = "user_id")
    private Long user;

    @NotNull
    @Column(name = "photo_id")
    private Long photo;

    @NotNull
    @Column(name = "date")
    private Timestamp date;

    @NotNull
    @Column(name = "rate")
    private Byte rate;

    public Long getRateID() {
        return rateID;
    }

    public void setRateID(Long rateID) {
        this.rateID = rateID;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Byte getRate() {
        return rate;
    }

    public void setRate(Byte rate) {
        this.rate = rate;
    }
}
