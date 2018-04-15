package com.example.imdb.imdb;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String identifier;
    private String title;
    private String poster;
    private String plot;
    private String release;
    private String director;

    public Movie(String identifier, String title) {
        this(identifier, title, null);
    }

    public Movie(String identifier, String title, String poster) {
        this(identifier, title, poster, null, null, null);
    }

    public Movie(String identifier, String title, String poster, String plot, String release, String director) {
        this.identifier = identifier;
        this.title = title;
        this.poster = poster;
        this.plot = plot;
        this.release = release;
        this.director = director;
    }

    private Movie(Parcel in) {
        this.identifier = in.readString();
        this.title = in.readString();
        this.poster = in.readString();
        this.plot = in.readString();
        this.release = in.readString();
        this.director = in.readString();
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

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.identifier);
        out.writeString(this.title);
        out.writeString(this.poster);
        out.writeString(this.plot);
        out.writeString(this.release);
        out.writeString(this.director);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
