package com.benjamin.interact.movieLibrary.controller;

import com.benjamin.interact.movieLibrary.entity.Movie;
import com.benjamin.interact.movieLibrary.exception.NotFoundException;
import com.benjamin.interact.movieLibrary.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

/**
 * Movie Controller.
 * @author Benjamin Robert
 * @version 1.0
 */
@RestController
public class MoviesController {

    private MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService){
        this.moviesService = moviesService;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public Set<Movie> selectAll() {
        return moviesService.selectAll() ;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public Movie selectMovie(@PathVariable("id") String id){
        return moviesService.selectOne(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/movies", method = RequestMethod.POST, consumes = "application/json")
    public Movie addMovie(@RequestBody Movie movie){
        return moviesService.add(movie);
    }

}
