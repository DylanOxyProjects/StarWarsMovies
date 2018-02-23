package com.dylan.starwarsmovies;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;


public class Movie {

    public String title;
    public int episode_number;
    public JSONArray main_characters;
    public String description;
    public String url;
    public String poster;
    public String seen;


    // static methods that read the json file in and load into Movie
    // static method that loads our recipes.json using the helper method
    // this method will return an array list of movies from the JSON file


    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();


        // try to read from JSON file
        // get information by using the tags
        // construct a Movie Object for each movie in movies.JSON
        // add the object to arraylist
        // return arraylist

        try {
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");


            for (int i = 0; i < movies.length(); i++) {
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.episode_number = movies.getJSONObject(i).getInt("episode_number");
                movie.main_characters = movies.getJSONObject(i).getJSONArray("main_characters");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.url = movies.getJSONObject(i).getString("url");
                movie.poster = movies.getJSONObject(i).getString("poster");
                //movie.seen = movies.getJSONObject(i).getString("seen");
                movie.seen = "Has Seen?";



                // add to arraylist
                movieList.add(movie);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }


    private static String loadJsonFromAsset(String filename, Context context){
        String json = null;

        try{
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
