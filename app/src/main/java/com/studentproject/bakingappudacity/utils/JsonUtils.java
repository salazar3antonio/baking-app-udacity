package com.studentproject.bakingappudacity.utils;

import android.util.Log;

import com.studentproject.bakingappudacity.database.models.Ingredient;
import com.studentproject.bakingappudacity.database.models.Recipe;
import com.studentproject.bakingappudacity.database.models.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static List<Recipe> getRecipesFromJson(String jsonResponse) throws IOException, JSONException {

        //instantiate a new List. This will store all of the Recipe Objects
        List<Recipe> recipes = new ArrayList<>();

        //grab the top level JSONArray
        JSONArray recipesJsonArray = new JSONArray(jsonResponse);

        for (int i = 0; i < recipesJsonArray.length(); i++) {

            //grab the JSONObject which holds the individual Recipe key/values
            JSONObject recipeObject = recipesJsonArray.getJSONObject(i);

            int recipeId = recipeObject.optInt("id");
            String recipeName = recipeObject.optString("name");
            int servings = recipeObject.optInt("servings");

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
                Log.e(TAG, "Steps from JSON Error: ", e);
            }

            Log.i(TAG, "Recipe name " + recipeName);

            //build up our Recipe Objects with the values from the JSON resource
            Recipe recipe = new Recipe(recipeId, recipeName, ingredients, steps, servings);
            recipes.add(recipe);

        }

        return recipes;

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
