package com.benjamin.interact.movieLibrary.entity;

import java.util.Date;
import java.util.UUID;

/**
 *
 * Movie POJO.
 * Each movie have a unique id generated with UUID.
 * @author Benjamin Robert
 * @version 1.0
 *
 */
public class Movie {

    /**
     * Id of the movie
     */
    private UUID id;

    /**
     * Title of the movie
     */
    private String title;

    /**
     * Date of the movie
     */
    private Date releaseDate;

    /**
     * Private constructor to create a movie entity.
     * @param id of the movie
     * @param title of the movie
     * @param releaseDate of the movie
     */
    private Movie(UUID id, String title, Date releaseDate){
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    /**
     * Public constructor to create a movie entity.
     * The constructor generate a randomUUID for each movie.
     * @param title of the movie
     * @param releaseDate of the movie
     */
    public Movie(String title, Date releaseDate){
        this(UUID.randomUUID(), title, releaseDate);
    }

    /**
     * Accessor to the Id
     * @return id of the mvie
     */
    public UUID getId(){
        return this.id ;
    }

    /**
     * Accessor to the title
     * @return title of the movie
     */
    public String getTitle(){
        return this.title ;
    }

    /**
     * Accessor to the realease date
     * @return the realease date of the movie
     */
    public Date getReleaseDate(){
        return this.releaseDate ;
    }
}
