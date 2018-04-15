package com.example.imdb.imdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {

    private static String MOVIE = "com.example.imdb.imdb.MOVIE";

    private MovieAccessor accessor = new MovieAccessor();

    ImageLoader imageLoader = ImageLoader.getInstance();

    private Movie movie = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_activity);

        Intent intent = getIntent();

        if (savedInstanceState != null && savedInstanceState.containsKey(MOVIE)) {
            movie = savedInstanceState.getParcelable(MOVIE);
        } else {
            try {
                movie = accessor.getMovieById(intent.getStringExtra(ListActivity.DETAILS_ACTIVITY_PARAM));
            } catch (ExecutionException | InterruptedException | IOException | JSONException e) {
                e.printStackTrace();
            }
        }

        ImageView poster = findViewById(R.id.poster);
        imageLoader.displayImage(movie.getPoster(), poster);

        TextView title = findViewById(R.id.title);
        title.setText(movie.getTitle());

        TextView director = findViewById(R.id.director);
        director.setText(movie.getDirector());

        TextView plot = findViewById(R.id.plot);
        plot.setText(movie.getPlot());

        TextView release = findViewById(R.id.release);
        release.setText(movie.getRelease());
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MOVIE, movie);
    }
}
