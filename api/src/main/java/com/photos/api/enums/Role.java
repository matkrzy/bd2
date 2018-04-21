package com.photos.api.enums;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */


public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private String text;

    Role(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
