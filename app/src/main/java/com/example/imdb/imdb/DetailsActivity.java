package com.example.imdb.imdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_activity);

        Intent intent = getIntent();
        Movie movie = MovieAccessor.getInstance().getMovieById(intent.getStringExtra(ListActivity.DETAILS_ACTIVITY_PARAM));

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.title);
        textView.setText(movie.getTitle());
    }
}
