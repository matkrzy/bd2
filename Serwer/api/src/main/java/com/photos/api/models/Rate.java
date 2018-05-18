package com.photos.api.models;

import org.springframework.hateoas.ResourceSupport;

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
    @Column(name = "user_email")
    private String user;

    @NotNull
    @Column(name = "photo_id")
    private Long photo;

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
