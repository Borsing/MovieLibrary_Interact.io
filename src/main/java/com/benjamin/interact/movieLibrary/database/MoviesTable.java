package com.benjamin.interact.movieLibrary.database;

import com.benjamin.interact.movieLibrary.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * MoviesTable simulate our data in a database with typical requests.
 * @author Benjamin Robert
 * @version 1.0
 *
 */
public class MoviesTable {

    /**
     * Set which contains each movie.
     */
    private Set<Movie> movies ;

    /**
     * Public constructor which initialize our set of movie.
     */
    public MoviesTable(){
        movies = new HashSet<>();
    }

    /**
     * Add a new movie in the table.
     * @param movie to add.
     * @return the movie added, with the uuid generated
     */
    public Movie add(Movie movie){
        movies.add(movie);
        return movie ;
    }

    /**
     * Update a movie in the table with its id
     * @param id id of the movie in the Set
     * @param movie with new fields
     * @return optional with the movie updated if the id exists, empty otherwise.
     */
    public Optional<Movie> update(UUID id, Movie movie){
        Optional<Movie> optMovie = this.selectOne(id);
        optMovie.ifPresent(m -> {
            m.setTitle(movie.getTitle());
            m.setReleaseDate(movie.getReleaseDate());
        });
        return optMovie;
    }

    /**
     * Remove specific movie.
     * @param id of the movie to remove
     * @return optional with the movie deleted if the id exists, empty otherwise.
     */
    public Optional<Movie> remove(UUID id){
        Optional<Movie> optMovie = this.selectOne(id);
        optMovie.ifPresent(m -> movies.remove(m));
        return optMovie ;
    }

    /**
     * Select all the movies
     * @return a set which contain all the movies
     */
    public Set<Movie> selectAll(){
        return movies ;
    }

    /**
     * Select one specific movie in the table
     * @param id of the movie
     * @return Optional which contain the movie if the id exists or Optional.empty otherwise.
     */
    public Optional<Movie> selectOne(UUID id){
        return movies.stream().filter(m -> m.getId().equals(id)).findFirst();
    }
}
