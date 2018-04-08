package com.photos.api.models;

import com.photos.api.enums.PhotoState;
import com.photos.api.enums.ShareState;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */

@Entity
@Table(name = "photo")
public class Photo extends ResourceSupport {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long photoID;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "path")
    private String path;

    @NotNull
    @Column(name = "upload_time")
    private Timestamp uploadTime;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "share_state")
    private ShareState shareState;

    @NotNull
    @Column(name = "photo_state")
    private PhotoState photoState;

//    @OneToOne
//    @JoinColumn(name = "exif_id")
//    private PhotoExif exif;
//
//    public PhotoExif getExif() {
//        return exif;
//    }
//
//    public void setExif(PhotoExif exif) {
//        this.exif = exif;
//    }

    public Photo() {
    }

    public Photo(String name, @NotNull User user, @NotNull String path, @NotNull Timestamp uploadTime, String description, @NotNull ShareState shareState, @NotNull PhotoState photoState) {
        this.name = name;
        this.user = user;
        this.path = path;
        this.uploadTime = uploadTime;
        this.description = description;
        this.shareState = shareState;
        this.photoState = photoState;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
