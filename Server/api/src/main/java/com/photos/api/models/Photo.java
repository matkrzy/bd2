package com.photos.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.photos.api.models.enums.PhotoState;
import com.photos.api.models.enums.ShareState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */

@Entity
@Table(name = "photo")
@ApiModel
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
    @OneToOne
    @JoinColumn(name = "owner")
    private User owner;

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

    public Photo(@NotNull String name, @NotNull User user, String path, @NotNull Timestamp uploadTime, String description, ShareState shareState, PhotoState photoState) {
        this.name = name;
        this.owner = user;
        this.path = path;
        this.uploadTime = uploadTime;
        this.description = description;
        this.shareState = shareState;
        this.photoState = photoState;
    }

    public Photo() {
    }

    public Photo(Long id) {
        this.photoID = id;
    }

    public Long getPhotoID() {
        return photoID;
    }

    @ApiModelProperty(readOnly = true)
    public void setPhotoID(Long photoID) {
        this.photoID = photoID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public User getUser() {
        return owner;
    }

    @JsonProperty
    @ApiModelProperty(hidden = true)
    public void setUser(User user) {
        this.owner = user;
    }

    @ApiModelProperty(readOnly = true)
    public String getowner_email() {
        return owner.getEmail();
    }

    @JsonIgnore
    public String getPath() {
        return path;
    }

    @JsonProperty
    @ApiModelProperty(hidden = true)
    public void setPath(String path) {
        this.path = path;
    }


    public Timestamp getUploadTime() {
        return uploadTime;
    }

    @ApiModelProperty(readOnly = true)
    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public ShareState getShareState() {
        return shareState;
    }

    @JsonProperty
    public void setShareState(ShareState shareState) {
        this.shareState = shareState;
    }

    @JsonIgnore
    public PhotoState getPhotoState() {
        return photoState;
    }

    @JsonProperty
    public void setPhotoState(PhotoState photoState) {
        this.photoState = photoState;
    }
}
