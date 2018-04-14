package com.example.imdb.imdb;

import java.util.ArrayList;
import java.util.List;

public class MovieAccessor {

    private List<Movie> movies = new ArrayList<>();

    public MovieAccessor(){
        movies.add(new Movie("123", "Intersellar"));
        movies.add(new Movie("456", "L'empire contre attaque"));
        movies.add(new Movie("789", "Avengers"));
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public Movie getMovieById(String identifier) {
        Movie foundMovie = null;
        for (Movie movie : this.movies) {
            if (movie.getIdentifier().equals(identifier)) {
                foundMovie = movie;
                break;
            }
        }
        return foundMovie;
    }
}
