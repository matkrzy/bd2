package com.photos.api.models.projections;

import com.photos.api.models.enums.PhotoState;
import com.photos.api.models.enums.ShareState;
import com.photos.api.models.Photo;
import org.springframework.hateoas.ResourceSupport;

import java.sql.Timestamp;

/**
 * @author Micha Królewski on 2018-04-29.
 * @version x
 */


public class PPhoto {

    private String name, path, description;

    private Timestamp uploadTime;

    private ShareState shareState;

    private PhotoState photoState;

    private String[] tags;

    public PPhoto() {
    }

    public PPhoto(Photo photo, String[] tags) {
        this.name = photo.getName();
        this.path = photo.getPath();
        this.description = photo.getDescription();
        this.uploadTime = photo.getUploadTime();
        this.shareState = photo.getShareState();
        this.photoState = photo.getPhotoState();
        this.tags = tags;
    }


    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
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
