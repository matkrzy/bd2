package com.photos.api.models;

import java.util.List;

/**
 * @author Micha Królewski on 2018-05-13.
 * @version x
 */


public class ResponsePhoto extends Photo {

    private byte rate;
    private List<Tag> tags;
    private Category category;

    public ResponsePhoto(Photo photo, byte rate, List<Tag> tags, Category category) {
        super(photo.getName(), photo.getUser(), photo.getUserID(), photo.getPath(), photo.getUploadTime(), photo.getDescription(), photo.getShareState(), photo.getPhotoState());
        this.setPhotoID(photo.getPhotoID());
        this.rate = rate;
        this.tags = tags;
        this.category = category;
    }

    public ResponsePhoto() {

    }

    public byte getRate() {
        return rate;
    }

    public void setRate(byte rate) {
        this.rate = rate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
