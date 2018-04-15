package com.example.imdb.imdb;

public class Movie {

    private String identifier;
    private String title;
    private String poster;

    public Movie(String identifier, String title) {
        this(identifier, title, null);
    }

    public Movie(String identifier, String title, String poster) {
        this.identifier = identifier;
        this.title = title;
        this.poster = poster;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
