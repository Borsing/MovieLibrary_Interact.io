package com.benjamin.interact.movieLibrary.database;

import com.benjamin.interact.movieLibrary.entity.Movie;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class MoviesTable {

    private Set<Movie> movies ;

    public MoviesTable(){
        movies = new HashSet<>();
    }

    public Movie add(Movie movie){
        movies.add(movie);
        return movie ;
    }

    public Optional<Movie> update(UUID id, Movie movie){
        Optional<Movie> optMovie = this.selectOne(id);
        optMovie.ifPresent(m -> {
            m.setTitle(movie.getTitle());
            m.setReleaseDate(movie.getReleaseDate());
        });
        return optMovie;
    }

    public Optional<Movie> remove(UUID id){
        Optional<Movie> optMovie = this.selectOne(id);
        optMovie.ifPresent(m -> movies.remove(m));
        return optMovie ;
    }

    public Set<Movie> selectAll(){
        return movies ;
    }

    public Optional<Movie> selectOne(UUID id){
        return movies.stream().filter(m -> m.getId().equals(id)).findFirst();
    }
}
