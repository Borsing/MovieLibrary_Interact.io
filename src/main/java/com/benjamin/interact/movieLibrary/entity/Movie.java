package com.benjamin.interact.movieLibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

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
     * release year of the movie
     */
    private int releaseYear;

    /**
     * Private constructor to create a movie entity.
     *
     * @param id          of the movie
     * @param title       of the movie
     * @param releaseYear of the movie
     */
    private Movie(UUID id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    /**
     * Public constructor to create a movie entity.
     * The constructor generate a randomUUID for each movie.
     *
     * @param title       of the movie
     * @param releaseYear of the movie
     */
    public Movie(@JsonProperty("title")String title, @JsonProperty("releaseYear") int releaseYear) {
        this(UUID.randomUUID(), title, releaseYear);
    }

    /**
     * Accessor to the Id
     *
     * @return id of the movie
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Accessor to the title
     *
     * @return title of the movie
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Accessor to the release year
     *
     * @return the release year of the movie
     */
    public int getReleaseDate() {
        return this.releaseYear;
    }

    /**
     * Setter for the title of the movie
     *
     * @param title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for the release year
     *
     * @param releaseYear of the movie
     */
    public void setReleaseDate(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Compare any object to the Movie.
     * @param obj to compare
     * @return true if the object is a Movie and all the fields are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Movie) {
            Movie m = (Movie) obj;
            return this.getId().equals(m.getId()) &&
                    this.getReleaseDate() == m.getReleaseDate() &&
                    this.getTitle().equals(m.getTitle()) ;
        }
        return false ;
    }

    /**
     * Hashcode of a movie extends from the UUID hashcode
     * @return unique hashcode
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * String which represents our Movie POJO
     * @return string with all fields
     */
    @Override
    public String toString() {
        return "Movie[" + this.getId() + ", " + this.getTitle() + ", " + this.getReleaseDate() +  "]";
    }
}
