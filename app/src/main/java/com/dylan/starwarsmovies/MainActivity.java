package com.dylan.starwarsmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public ListView listViewID;
    public Context contextID; // The Context class allows access to application-specific resources and classes, as well as up-calls for application-level operations such as launching activities, broadcasting and receiving intents, etc.
    public Toolbar toolbarID;;
    static ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewID = findViewById(R.id.movie_list_view);
        toolbarID = findViewById(R.id.toolBar1);
        toolbarID.setTitle(R.string.toolBarString);
        contextID = this;

        //final ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json", this);
        if (movieList == null){
            movieList = Movie.getMoviesFromFile("movies.json", this);
        }

        //create the adapter
        MovieAdapter adapter = new MovieAdapter(this, movieList);
        listViewID.setAdapter(adapter);  // In order to display items in the list, call setAdapter(ListAdapter) to associate an adapter with the list.

        listViewID.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Movie currentMovie = movieList.get(position);
                        Intent movieDetail = new Intent(contextID, MovieDetailActivity.class);

                        //I need to pass:
                        //movie title
                        //movie description
                        //movie picture

                        movieDetail.putExtra("movieTitle", currentMovie.title);
                        movieDetail.putExtra("movieDescription", currentMovie.description);
                        movieDetail.putExtra("moviePicture", currentMovie.poster);
                        movieDetail.putExtra("rowNumber", position);
                        startActivity(movieDetail);

                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        int row = bundle.getInt("row");
        String newRating = bundle.getString("newRating");
        movieList.get(row).seen = newRating;
        adapter = new MovieAdapter(this, movieList);
        listViewID.setAdapter(adapter);
    }
}













