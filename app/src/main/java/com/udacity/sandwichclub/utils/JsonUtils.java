package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String KEY_NAME = "name";
    private static final String KEY_MAIN_NAME = "mainName";
    private static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_INGREDIENTS = "ingredients";

    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwichJson = new JSONObject(json);

        /*Gets the names of the sandwich*/
        JSONObject sandwichName = sandwichJson.getJSONObject(KEY_NAME);
        String sandwichMainName = sandwichName.getString(KEY_MAIN_NAME);

        JSONArray sandwichAlternateName = sandwichName.getJSONArray(KEY_ALSO_KNOWN_AS);
        List<String> alternateNamesList = createListFromJSONArray(sandwichAlternateName);

        /*Get origin, description, image url, and ingredients*/
        String sandwichOrigin = sandwichJson.getString(KEY_PLACE_OF_ORIGIN);
        String sandwichDescription = sandwichJson.getString(KEY_DESCRIPTION);
        String sandwichImage = sandwichJson.getString(KEY_IMAGE);

        JSONArray sandwichIngredients = sandwichJson.getJSONArray(KEY_INGREDIENTS);
        List<String> ingredientsList = createListFromJSONArray(sandwichIngredients);

        /*Return sandwich object*/
        return new Sandwich(sandwichMainName, alternateNamesList, sandwichOrigin, sandwichDescription, sandwichImage, ingredientsList);
    }

    private static List<String> createListFromJSONArray(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < array.length(); i++) {
            list.add(array.getString(i));
        }
        return list;
    }
}
