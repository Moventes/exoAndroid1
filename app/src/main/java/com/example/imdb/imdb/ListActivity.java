package com.example.imdb.imdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListActivity extends AppCompatActivity {

    public static String DETAILS_ACTIVITY_PARAM = "com.example.imdb.imdb.DETAILS_ACTIVITY_PARAM";

    private MovieAccessor accessor = new MovieAccessor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_activity);
        final EditText search = (EditText) findViewById(R.id.search);
        Button submit = (Button) findViewById(R.id.submit);
        ListView listview = (ListView) findViewById(R.id.movies_list);

        final ListAdapter adapter = new ListAdapter(this, R.layout.list_item, new ArrayList<Movie>());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // update data in our adapter
                    adapter.getData().clear();
                    adapter.getData().addAll(accessor.getMoviesByTitle(search.getText().toString()));
                    // fire the event
                    adapter.notifyDataSetChanged();
                } catch (IOException | InterruptedException | ExecutionException | JSONException e) {
                    Log.e("ERROR", e.getMessage());
                    Toast.makeText(ListActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detailsActivity = new Intent(ListActivity.this, DetailsActivity.class);
                detailsActivity.putExtra(DETAILS_ACTIVITY_PARAM, accessor.getMovies().get(position).getIdentifier());
                ListActivity.this.startActivity(detailsActivity);
            }
        });
    }
}
