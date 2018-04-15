package com.example.imdb.imdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Movie> {

    private final Context context;
    private ArrayList<Movie> movies;

    ImageLoader imageLoader = ImageLoader.getInstance();

    public ListAdapter(@NonNull Context context, int resource, ArrayList<Movie> movies) {
        super(context, resource, movies);
        this.context = context;
        this.movies = movies;

        imageLoader.init(ImageLoaderConfiguration.createDefault(context.getApplicationContext()));
    }

    public ArrayList<Movie> getData() {
        return this.movies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.title);
        textView.setText(movies.get(position).getTitle());

        ImageView poster = (ImageView) rowView.findViewById(R.id.poster);
        imageLoader.displayImage(movies.get(position).getPoster(), poster);

        return rowView;
    }
}
