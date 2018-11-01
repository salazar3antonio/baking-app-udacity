package com.studentproject.bakingappudacity.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.studentproject.bakingappudacity.R;
import com.studentproject.bakingappudacity.data.Ingredient;
import com.studentproject.bakingappudacity.data.Recipe;
import com.studentproject.bakingappudacity.data.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    private int mRawJsonFile;
    private Resources mResources;

    public JsonUtils(Context context, int rawJsonFile) {
        this.mRawJsonFile = rawJsonFile;
        this.mResources = context.getResources();
    }

    public static void readRecipesFromResource(Context context) throws IOException, JSONException {

        //converting the JSON raw file into a String value so Java can read it
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.recipes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        //Parse resource into key/values
        final String rawJson = builder.toString();
        Log.i(TAG, rawJson);

        JSONArray recipesArray = new JSONArray(rawJson);

        List<Recipe> recipes = new ArrayList<>();

        for (int i = 0; i < recipesArray.length(); i++) {

            JSONObject recipeObject = recipesArray.getJSONObject(i);

            int recipeId = recipeObject.optInt("id");
            String recipeName = recipeObject.optString("name");

            List<Ingredient> ingredients = null;
            try {
                ingredients = getIngredientsFromJson(recipeObject);
            } catch (JSONException e) {
                Log.e(TAG, "Ingredients from JSON Error: ", e);
            }

            List<Step> steps = null;
            try {
                steps = getStepsFromJson(recipeObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }



            Log.i(TAG, "recipe name " + recipeName);

            Recipe recipe = new Recipe(recipeId, recipeName, ingredients, steps, 0);
            recipes.add(recipe);

        }


    }

    private static List<Step> getStepsFromJson(JSONObject recipeObject) throws JSONException {

        List<Step> steps = new ArrayList<>();

        JSONArray stepsArray = recipeObject.getJSONArray("steps");

        for (int i = 0; i < stepsArray.length(); i++) {

            JSONObject ingredientObject = stepsArray.getJSONObject(i);

            int id = ingredientObject.optInt("id");
            String shortDescription = ingredientObject.optString("shortDescription");
            String description = ingredientObject.optString("description");
            String videoUrl = ingredientObject.optString("videoURL");
            String thumbnailUrl = ingredientObject.optString("thumbnailURL");

            Log.i(TAG, "Steps " + shortDescription);

            Step step = new Step(id, shortDescription, description, videoUrl, thumbnailUrl);

            steps.add(step);

        }

        return steps;
    }

    private static List<Ingredient> getIngredientsFromJson(JSONObject recipeObject) throws JSONException {

        List<Ingredient> ingredients = new ArrayList<>();

        JSONArray ingredientsArray = recipeObject.getJSONArray("ingredients");

        for (int i = 0; i < ingredientsArray.length(); i++) {

            JSONObject ingredientObject = ingredientsArray.getJSONObject(i);

            int quantity = ingredientObject.optInt("quantity");
            String measure = ingredientObject.optString("measure");
            String ingredientDescription = ingredientObject.optString("ingredient");

            Log.i(TAG, "Ingredients " + ingredientDescription);

            Ingredient ingredient = new Ingredient(quantity, measure, ingredientDescription);

            ingredients.add(ingredient);

        }

        return ingredients;

    }

}
