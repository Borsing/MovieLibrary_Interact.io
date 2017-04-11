package com.benjamin.interact.movieLibrary.service;

import com.benjamin.interact.movieLibrary.database.MoviesTable;
import com.benjamin.interact.movieLibrary.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Movie service.
 * @author Benjamin Robert
 * @version 1.0
 *
 */
@Service
public class MoviesService {

    /**
     * MoviesTable which contains our movies in a set structure.
     */
    private MoviesTable moviesTable ;

    /**
     * Public constructor autowired by Spring boot
     * @param moviesTable
     */
    @Autowired
    public MoviesService(MoviesTable moviesTable){
        this.moviesTable = moviesTable ;
    }

    /**
     * Add a movie in the table.
     * @param movie to add.
     * @return the movie added with the UUID generated.
     */
    public Movie add(Movie movie){
        return this.moviesTable.add(movie);
    }

    /**
     * Remove a movie in the table.
     * @param id of the movie to remove.
     * @return Optional which contains the movie removed, empty otherwise.
     */
    public Optional<Movie> remove(UUID id){
        return moviesTable.remove(id);
    }

    /**
     * Update a movie in the table
     * @param id of the movie
     * @param movie with updated fields
     * @return optional which contains the movie updated, empty otherwise.
     */
    public Optional<Movie> update(UUID id, Movie movie){
        return this.moviesTable.update(id,movie);
    }

    /**
     * Select one specific movie in the table by its id.
     * @param id of the movie
     * @return Optional which contains the movie if the id exits, empty otherwise.
     */
    public Optional<Movie> selectOne(UUID id){
        return this.moviesTable.selectOne(id);
    }

    /**
     * Select all the movies in our table.
     * @return all the movies
     */
    public Set<Movie> selectAll(){
        return this.moviesTable.selectAll();
    }

    /**
     * Select all the movies from a specific year.
     * @param year of the movies
     * @return a set which contains all the movies
     */
    public Set<Movie> selectByYear(Year year){
        return this.moviesTable.selectAll().stream().
                filter(m -> m.getReleaseDate() == year.getValue()).
                collect(Collectors.toSet());
    }
}
