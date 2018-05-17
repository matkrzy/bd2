package com.photos.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Micha Królewski on 2018-04-08.
 * @version 1.0
 */

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long categoryID;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "user_email")
    private String user;

    @Column(name = "parent")
    private String parentCategory;

    public Category() {
    }

    public Category(@NotNull String name, @NotNull String user, @NotNull String parentCategory) {

        this.name = name;
        this.user = user;
        this.parentCategory = parentCategory;
    }

    public Long getCategoryID() {

        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
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

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }
}
