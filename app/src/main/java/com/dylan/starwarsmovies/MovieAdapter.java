package com.dylan.starwarsmovies;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import java.util.ArrayList;


public class MovieAdapter extends BaseAdapter{


    private Context contextID;
    private ArrayList<Movie> movieListID;
    private LayoutInflater inflatorID;


    //constructor
    public MovieAdapter(Context contextID, ArrayList<Movie> movieListID){
        //initialize instance variables
        this.contextID = contextID;
        this.movieListID = movieListID;
        //inflatorID = (LayoutInflater)inflatorID.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Instantiates a layout XML file into its corresponding View objects.
        // Use getSystemService(Class)
        // to retrieve a standard LayoutInflater instance that is already hooked up to the current context
        // and correctly configured for the device you are running on.
        this.inflatorID = (LayoutInflater)contextID.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }




    @Override
    public int getCount() {
        return movieListID.size();
    }

    @Override
    public Object getItem(int i) {
        return movieListID.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //View class represents the basic building block for user interface components.
        // A View occupies a rectangular area on the screen and is responsible for drawing and event handling.
        // View is the base class for widgets, which are used to create interactive UI components (buttons, text fields, etc.).

        // The ViewGroup subclass is the base class for layouts,
        // which are invisible containers that hold other Views (or other ViewGroups) and define their layout properties.

        // create a ViewHolder variable called holder that will hold other views or viewgroups
        ViewHolder holder;

        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyID again

        if (view == null){
            //inflate
            view = inflatorID.inflate(R.layout.list_item_movie, viewGroup, false);
            // add views to the holder
            holder = new ViewHolder();
            //views
            holder.titleTextView = view.findViewById(R.id.movie_list_title);
            holder.descriptionView = view.findViewById(R.id.movie_list_description);
            holder.characterTextView = view.findViewById(R.id.movie_list_characters);
            holder.moviePicView = view.findViewById(R.id.movie_list_picture);
            holder.seenView = view.findViewById(R.id.seen);
            // add the holder to the view
            // for future use
            view.setTag(holder);
        }
        else{
            // get the view holder from converview
            holder = (ViewHolder)view.getTag();
        }
        // get relavate subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView descriptionView = holder.descriptionView;
        TextView characterTextView = holder.characterTextView;
        ImageView movieImageView = holder.moviePicView;
        TextView seenView = holder.seenView;

        // get corresonpinding movie for each row
        Movie movie= (Movie)getItem(i);


        // update the row view's textviews and imageview to display the information

        // titleTextView
        titleTextView.setText(movie.title);
        titleTextView.setTextColor(ContextCompat.getColor(contextID, R.color.Black));


        //description text view
        descriptionView.setEllipsize(TextUtils.TruncateAt.END);
        descriptionView.setMaxLines(3);
        descriptionView.setText(movie.description);
        descriptionView.setTextColor(ContextCompat.getColor(contextID, R.color.Black));

        //setting up main character text

        String allCharacters = "";

        try {
            String mainCharacter1 = movie.main_characters.getString(0);
            String mainCharacter2 = movie.main_characters.getString(1);
            String mainCharacter3 = movie.main_characters.getString(2);
            allCharacters = mainCharacter1 + ", " +  mainCharacter2 + ", " + mainCharacter3;


        } catch (JSONException e) {
            e.printStackTrace();
        }


        // characterTextView
        characterTextView.setText(allCharacters);
        characterTextView.setTextColor(ContextCompat.getColor(contextID, R.color.Black));

        // imageView
        // use Picasso library to load image from the image url


        Picasso.with(contextID).load(movie.poster).into(movieImageView);



        seenView.setText(movie.seen);
        seenView.setTextColor(ContextCompat.getColor(contextID, R.color.red));

        return view;
    }

    private static class ViewHolder{
        TextView titleTextView;
        TextView descriptionView;
        TextView characterTextView;
        ImageView moviePicView;
        TextView seenView;
    }


}

