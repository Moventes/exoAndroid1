package com.example.imdb.imdb;

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

    public MovieAccessor(){
    }

    public List<Movie> getMoviesByTitle(String title) throws ExecutionException, InterruptedException, IOException, JSONException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(OMDB_ENTRY_POINT).newBuilder();
        urlBuilder.addQueryParameter("s", title);
        String url = urlBuilder.build().toString();

        NetworkRequestLauncher launcher = new NetworkRequestLauncher();
        Response response = launcher.execute(url).get();

        JSONObject object = (JSONObject) new JSONTokener(response.body().string()).nextValue();

        JSONArray array = object.getJSONArray("Search");

        List<Movie> movies = new ArrayList<Movie>();

        for (int rank = 0; rank < array.length(); rank++) {
            JSONObject item = array.getJSONObject(rank);
            movies.add(parseJsonObject(item));
        }

        return movies;
    }

    private Movie parseJsonObject(JSONObject item) throws JSONException {
        return new Movie(item.getString("imdbID"), item.getString("Title"), item.getString("Poster"));
    }

    public Movie getMovieById(String identifier) throws ExecutionException, InterruptedException, IOException, JSONException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(OMDB_ENTRY_POINT).newBuilder();
        urlBuilder.addQueryParameter("i", identifier);
        String url = urlBuilder.build().toString();

        NetworkRequestLauncher launcher = new NetworkRequestLauncher();
        Response response = launcher.execute(url).get();

        JSONObject object = (JSONObject) new JSONTokener(response.body().string()).nextValue();

        return parseJsonObject(object);
    }
}
