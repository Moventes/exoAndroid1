package com.example.imdb.imdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {

    private MovieAccessor accessor = new MovieAccessor();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_activity);

        Intent intent = getIntent();
        Movie movie = null;
        try {
            movie = accessor.getMovieById(intent.getStringExtra(ListActivity.DETAILS_ACTIVITY_PARAM));
        } catch (ExecutionException | InterruptedException | IOException | JSONException e) {
            e.printStackTrace();
        }

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.title);
        textView.setText(movie.getTitle());
    }
}
