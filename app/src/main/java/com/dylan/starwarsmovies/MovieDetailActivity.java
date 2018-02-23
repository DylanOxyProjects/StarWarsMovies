package com.dylan.starwarsmovies;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toolbar;
import android.view.View;
import com.squareup.picasso.Picasso;


public class MovieDetailActivity extends AppCompatActivity{

    public int position;


    // override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbarView = findViewById(R.id.toolBar2);
        TextView titleTextView = findViewById(R.id.titleView);
        TextView descriptionView = findViewById(R.id.descriptionView);
        ImageView imageView = findViewById(R.id.imgView);

        //the bundle holds the data transfered from the main activity
        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("rowNumber");

        if (bundle != null) {
            toolbarView.setTitle(bundle.getString("movieTitle"));
            titleTextView.setText(bundle.getString("movieTitle"));
            descriptionView.setText(bundle.getString("movieDescription"));
            descriptionView.setMovementMethod(new ScrollingMovementMethod());
            Picasso.with(this).load(bundle.getString("moviePicture")).into(imageView);
        }
    }

        public void onRadioButtonClicked(View view){

        // is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        //check to see which button was pressed
        switch (view.getId()){
            case R.id.alreadySeenID:
                if (checked){
                    Intent movieRating = new Intent(this, MainActivity.class);
                    String rating = "Already Seen";
                    movieRating.putExtra("newRating", rating);
                    movieRating.putExtra("row", position);
                    startActivity(movieRating);
                }

                break;
            case R.id.wantSeeID:
                if (checked){
                    Intent movieRating = new Intent(this, MainActivity.class);
                    String rating = "Want to See";
                    movieRating.putExtra("newRating", rating);
                    movieRating.putExtra("row", position);
                    startActivity(movieRating);
                }

                break;
            case R.id.doNotLikeID:
                if (checked){
                    Intent movieRating = new Intent(this, MainActivity.class);
                    String rating = "Do Not Like";
                    movieRating.putExtra("newRating", rating);
                    movieRating.putExtra("row", position);
                    startActivity(movieRating);
                }
                break;
        }
    }
}


// so what if I do the same thing I did before, create a new intent from this class and send it over to main activity