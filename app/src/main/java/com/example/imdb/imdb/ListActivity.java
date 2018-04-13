package com.example.imdb.imdb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_activity);
        ListView listview = (ListView) findViewById(R.id.movies_list);

        final List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Intersellar"));
        movies.add(new Movie("L'empire contre attaque"));
        movies.add(new Movie("Avengers"));

        ListAdapter adapter = new ListAdapter(this, R.layout.list_item, movies);

        listview.setAdapter(adapter);
    }
}
