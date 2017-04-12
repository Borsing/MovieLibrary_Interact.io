package com.benjamin.interact.movieLibrary.controller;

import com.benjamin.interact.movieLibrary.entity.Movie;
import com.benjamin.interact.movieLibrary.exception.NotFoundException;
import com.benjamin.interact.movieLibrary.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Set;
import java.util.UUID;

/**
 * Movie Controller.
 * @author Benjamin Robert
 * @version 1.0
 */
@RestController
public class MoviesController {

    /**
     * Movie Service used by the controller.
     */
    private MoviesService moviesService;

    /**
     * Public constructor autowired by Spring Boot.
     * @param moviesService
     */
    @Autowired
    public MoviesController(MoviesService moviesService){
        this.moviesService = moviesService;
    }

    /**
     * Select all the movies.
     * HttpMethod : GET
     * URL : /movies
     * @return a set which contains our movies.
     */
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public Set<Movie> selectAll() {
        return moviesService.selectAll() ;
    }

    /**
     * Select a movie from a specific id
     * HttpMethod : GET
     * URL : /movies/{id}
     * @param id of the movie
     * @return the movie if it exits, throw a NotFoundException otherwise
     */
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public Movie selectMovie(@PathVariable("id") String id){
        return moviesService.selectOne(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException());
    }

    /**
     * Add a movie.
     * HttpMethod : POST
     * URL : /movies
     * @param movie to add
     * @return the movie added
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/movies", method = RequestMethod.POST, consumes = "application/json")
    public Movie addMovie(@RequestBody Movie movie){
        return moviesService.add(movie);
    }

    /**
     * Update a movie from a specific id
     * HttpMethod : PUT
     * URL : /movies/{id}
     * @param id of the movie
     * @param movie with the updated fields.
     * @return the movie updated if it exits, throw a NotFoundException otherwise
     */
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public Movie updateMovie(@PathVariable("id") String id, @RequestBody Movie movie){
        return moviesService.update(UUID.fromString(id), movie).
                orElseThrow(() -> new NotFoundException());
    }

    /**
     * Remove a movie from a specific id
     * HttpMethod : DELETE
     * URL : /movies/{id}
     * @param id of the movie
     * @return the movie deleted if it exits, throw a NotFoundException otherwise
     */
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public Movie removeMovie(@PathVariable("id") String id){
        return moviesService.remove(UUID.fromString(id)).
                orElseThrow(() -> new NotFoundException());
    }

    /**
     * Select all the movies from a specific Year
     * HttpMethod : GET
     * URL : /movies/releaseyear/{releaseYear}
     * @param releaseYear of the movies
     * @return the movies of a specific year
     */
    @RequestMapping(value = "/movies/releaseyear/{releaseYear}", method = RequestMethod.GET)
    public Set<Movie> selectByYear(@PathVariable("releaseYear") int releaseYear){
        return moviesService.selectByYear(Year.of(releaseYear));
    }

}
