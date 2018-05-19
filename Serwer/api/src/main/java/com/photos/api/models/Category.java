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
    @Column(name = "user_id")
    private Long user;


    @Column(name = "parent_id")
    private Long parentCategory;

    public Category(@NotNull String name, @NotNull Long user, Long parentCategory) {

        this.name = name;
        this.user = user;
        this.parentCategory = parentCategory;
    }

    public Category() {
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Long parentCategory) {
        this.parentCategory = parentCategory;
    }
}


