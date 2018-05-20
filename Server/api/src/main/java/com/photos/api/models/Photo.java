package com.photos.api.models;

import com.photos.api.models.enums.PhotoState;
import com.photos.api.models.enums.ShareState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long photoID;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "user_email")
    private String user;

    @NotNull
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "path")
    private String path;

    @NotNull
    @Column(name = "upload_time")
    private Timestamp uploadTime;

    @Column(name = "description")
    private String description;

    @Column(name = "share_state")
    private ShareState shareState;

    @Column(name = "photo_state")
    private PhotoState photoState;

    public Photo(@NotNull String name, @NotNull String user, @NotNull Long userid, String path, @NotNull Timestamp uploadTime, String description, ShareState shareState, PhotoState photoState) {

        this.name = name;
        this.user = user;
        this.userID = userid;
        this.path = path;
        this.uploadTime = uploadTime;
        this.description = description;
        this.shareState = shareState;
        this.photoState = photoState;
    }

    public Photo() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userid) {
        this.userID = userid;
    }

    public Long getPhotoID() {
        return photoID;
    }

    public void setPhotoID(Long photoID) {
        this.photoID = photoID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShareState getShareState() {
        return shareState;
    }

    public void setShareState(ShareState shareState) {
        this.shareState = shareState;
    }

    public PhotoState getPhotoState() {
        return photoState;
    }

    public void setPhotoState(PhotoState photoState) {
        this.photoState = photoState;
    }
}
