package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        final String name = "name";
        final String mainName = "mainName";
        final String alsoKnownAs = "alsoKnownAs";
        final String placeOfOrigin = "placeOfOrigin";
        final String description = "description";
        final String image = "image";
        final String ingredients = "ingredients";

        JSONObject sandwichJson = new JSONObject(json);

        /*Gets the names of the sandwich*/
        JSONObject sandwichName = sandwichJson.getJSONObject(name);
        String sandwichMainName = sandwichName.getString(mainName);

        JSONArray sandwichAlternateName = sandwichName.getJSONArray(alsoKnownAs);
        List<String> alternateNamesList = new ArrayList<String>();

        for (int i = 0; i < sandwichAlternateName.length(); i++) {
            alternateNamesList.add(sandwichAlternateName.getString(i));
        }

        /*Get origin, description, image url, and ingredients*/
        String sandwichOrigin = sandwichJson.getString(placeOfOrigin);
        String sandwichDescription = sandwichJson.getString(description);
        String sandwichImage = sandwichJson.getString(image);

        JSONArray sandwichIngredients = sandwichJson.getJSONArray(ingredients);

        List<String> ingredientsList = new ArrayList<String>();

        for (int i = 0; i < sandwichIngredients.length(); i++) {
            ingredientsList.add(sandwichIngredients.getString(i));
        }

        /*Return sandwich object*/
        return new Sandwich(sandwichMainName, alternateNamesList, sandwichOrigin, sandwichDescription, sandwichImage, ingredientsList);

    }
}
