package com.benjamin.interact.movieLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Movie Application
 * @author Benjamin Robert
 * @version 1.0
 */
@SpringBootApplication
public class MoviesApplication {

    /**
     * Run the movie micro service.
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(MoviesApplication.class,args);
    }
}