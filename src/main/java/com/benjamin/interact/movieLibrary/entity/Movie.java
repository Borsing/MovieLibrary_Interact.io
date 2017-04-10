package com.benjamin.interact.movieLibrary.entity;

import java.util.Date;
import java.util.UUID;

/**
 * Created by borsing on 10/04/17.
 */
public class Movie {

    private UUID id;
    private String title;
    private Date releaseDate;

    private Movie(UUID id, String title, Date releaseDate){
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Movie(String title, Date releaseDate){
        this(UUID.randomUUID(), title, releaseDate);
    }

    public UUID getId(){
        return this.id ;
    }

    public String getTitle(){
        return this.title ;
    }

    public Date getReleaseDate(){
        return this.releaseDate ;
    }
}
