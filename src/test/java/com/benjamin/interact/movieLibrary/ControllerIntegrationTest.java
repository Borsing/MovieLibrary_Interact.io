package com.benjamin.interact.movieLibrary;

import com.benjamin.interact.movieLibrary.entity.Movie;
import com.benjamin.interact.movieLibrary.service.MoviesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * Integration Test on all the services available.
 * @author Benjamin Robert
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoviesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {

    /**
     * port used of the Local Server
     */
    @LocalServerPort
    private int port;

    /**
     * TestRestTemplate to do HTTP request to the micro Service
     */
    @Autowired
    private TestRestTemplate template ;

    /**
     * Movie Service
     */
    @Autowired
    private MoviesService moviesService ;

    /**
     * Integration Test to select all the movies
     */
    @Test
    public void testSelectAllMovies() {
        //Set Url
        String url = "http://localhost:" + port + "/movies" ;

        //Populate
        moviesService.add(new Movie("movie1",1985));
        moviesService.add(new Movie("movie2",2001));

        //Get Objects From Service
        Set<Movie> moviesFromService = moviesService.selectAll();

        //Http Request
        ResponseEntity<Set<Movie>> entity = this.template.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Set<Movie>>() {});

        //Test Status is OK
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        //Get Object From Controller
        Set<Movie> moviesFromRest = entity.getBody();

        //Test Objects are equals
        then(moviesFromService).isEqualTo(moviesFromRest);
    }

    /**
     * Integration Test to select one specific movie
     */
    @Test
    public void testSelectOneMovie(){
        //Populate
        Movie movie = new Movie("movie1",1910);
        UUID id = movie.getId();
        moviesService.add(movie);
        moviesService.add(new Movie("movie2",1900));

        //Set Url
        String url = "http://localhost:" + port + "/movies/" + id ;

        //Http Request
        ResponseEntity<Movie> entity = this.template.getForEntity(url,Movie.class);

        //Test Object equals Field by Field
        then(entity.getBody()).isEqualToComparingFieldByField(movie);
    }

    /**
     * Integration Test to select movies from a specific year
     */
    @Test
    public void testSelectByYear(){
        int year = 2015 ;
        //Set Url
        String url = "http://localhost:" + port + "/movies/releaseyear/" + year ;

        Set<Movie> moviesYear = new HashSet<>();

        moviesYear.add(new Movie("movie1",year));
        moviesYear.add(new Movie("movie2",year));


        //Populate
        moviesYear.forEach(m -> moviesService.add(m));
        moviesService.add(new Movie("movie2",year+1));

        //Http Request
        ResponseEntity<Set<Movie>> entity = this.template.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Set<Movie>>() {});

        //Test Status is OK
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        //Get Object From Controller
        Set<Movie> moviesFromRest = entity.getBody();

        //Test Objects are equals
        then(moviesYear).isEqualTo(moviesFromRest);
    }

    /**
     * Integration Test to add a movie.
     */
    @Test
    public void testAddMovie() {
        //Set Url
        String url = "http://localhost:" + port + "/movies";

        String title = "movie1";
        int releaseYear = 2007;

        //Http Request
        ResponseEntity<Movie> entity = this.template.postForEntity(url, new Movie(title,releaseYear), Movie.class);

        Optional<Movie> movieAdded = moviesService.selectOne(entity.getBody().getId());

        //Test object is present
        then(movieAdded.isPresent()).isTrue() ;

        //Test Fields
        movieAdded.ifPresent(m -> then(m).hasFieldOrPropertyWithValue("title",title));
        movieAdded.ifPresent(m -> then(m).hasFieldOrPropertyWithValue("releaseYear",releaseYear));

    }

    /**
     * Integration Test to update a movie
     */
    @Test
    public void testUpdateMovie(){

        //Populate
        Movie movie = new Movie("movie1",2008);
        moviesService.add(movie);

        //Set Url
        String url = "http://localhost:" + port + "/movies/" + movie.getId();

        //New fields
        String title = "newMovie1";
        int releaseYear = 2007;

        //Http Request
        this.template.put(url, new Movie(title,releaseYear));

        //Test Fields
       then(movie).hasFieldOrPropertyWithValue("title",title);
       then(movie).hasFieldOrPropertyWithValue("releaseYear",releaseYear);
    }

    /**
     * Integration Test to delete a movie.
     */
    @Test
    public void testDeleteMovie(){

        //Populate
        Movie movie = new Movie("movie1",2008);
        moviesService.add(movie);

        //Set Url
        String url = "http://localhost:" + port + "/movies/" + movie.getId();

        //Http Request
        this.template.delete(url);

        //Test Fields
        then(moviesService.selectOne(movie.getId()).isPresent()).isFalse();
    }
}

