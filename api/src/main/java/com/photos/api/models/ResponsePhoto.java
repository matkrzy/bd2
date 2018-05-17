package com.photos.api.models;

/**
 * @author Micha Królewski on 2018-05-13.
 * @version x
 */


public class ResponsePhoto extends Photo {

    private byte rate;
    private String[] tags;
    private String category;

    public ResponsePhoto(Photo photo, byte rate, String[] tags, String category) {
        super(photo.getName(), photo.getUser(), photo.getPath(), photo.getUploadTime(), photo.getDescription(), photo.getShareState(), photo.getPhotoState(), photo.getExif());
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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
