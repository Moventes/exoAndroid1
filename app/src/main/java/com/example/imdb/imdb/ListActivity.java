package com.example.imdb.imdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static String DETAILS_ACTIVITY_PARAM = "com.example.imdb.imdb.DETAILS_ACTIVITY_PARAM";

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
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detailsActivity = new Intent(ListActivity.this, DetailsActivity.class);
                detailsActivity.putExtra(DETAILS_ACTIVITY_PARAM, movies.get(position).getTitle());
                ListActivity.this.startActivity(detailsActivity);
            }
        });
    }
}
