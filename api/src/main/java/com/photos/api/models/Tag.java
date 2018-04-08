package com.photos.api.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Micha Królewski on 2018-04-07.
 * @version 1.0
 */


@Entity
@Table(name = "tag")
public class Tag {

    @Id @GeneratedValue
    @NotNull
    @Column(name = "id")
    private Long tagID;

    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @NotBlank
    @Column(name = "name")
    private String name;
}
