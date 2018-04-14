package com.example.imdb.imdb;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.HttpUrl;
import okhttp3.Response;

public class MovieAccessor {

    private static String OMDB_ENTRY_POINT = "http://www.omdbapi.com/?apikey=172d5c07";

    private List<Movie> movies = new ArrayList<>();

    public MovieAccessor(){
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public List<Movie> getMoviesByTitle(String title) throws ExecutionException, InterruptedException, IOException, JSONException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(OMDB_ENTRY_POINT).newBuilder();
        urlBuilder.addQueryParameter("s", title);
        String url = urlBuilder.build().toString();

        NetworkRequestLauncher launcher = new NetworkRequestLauncher();
        Response response = launcher.execute(url).get();

        JSONObject object = (JSONObject) new JSONTokener(response.body().string()).nextValue();

        this.movies = parseJsonArray(object.getJSONArray("Search"));

        return movies;
    }

    private List<Movie> parseJsonArray(JSONArray array) throws JSONException {
        List<Movie> movies = new ArrayList<Movie>();

        for (int rank = 0; rank < array.length(); rank++) {
            JSONObject item = array.getJSONObject(rank);
            Movie movie = new Movie(item.getString("imdbID"), item.getString("Title"));
            movies.add(movie);
        }

        return movies;
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
