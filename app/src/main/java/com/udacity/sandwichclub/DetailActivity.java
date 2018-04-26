package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = null;

        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        String point = "\u25CF";

        // set also known as view
        TextView alsoKnownIv = (TextView) findViewById(R.id.also_known_tv);

        for (String name : sandwich.getAlsoKnownAs()) {
            String str = alsoKnownIv.getText().toString();
            alsoKnownIv.setText(str += (point + " " + name + "\n"));
        }

        // set ingredients view
        TextView ingredientsIv = (TextView) findViewById(R.id.ingredients_tv);

        for (String ingredient : sandwich.getIngredients()) {
            String str = ingredientsIv.getText().toString();
            ingredientsIv.setText(str += (point + " " + ingredient + "\n"));
        }

        // set origin view
        TextView originIv = (TextView) findViewById(R.id.origin_tv);
        if(sandwich.getPlaceOfOrigin().length() > 0) {
            originIv.setText(point + " " + sandwich.getPlaceOfOrigin() + "\n");
        }

        // set description view
        TextView descriptionIv = (TextView) findViewById(R.id.description_tv);
        descriptionIv.setText(sandwich.getDescription());

    }
}
