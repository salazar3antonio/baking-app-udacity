package com.studentproject.bakingappudacity.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.studentproject.bakingappudacity.database.models.Ingredient;
import com.studentproject.bakingappudacity.database.models.Step;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Converters {

    //this class converts ArrayList<Object> to JSON and vice versa.

    @TypeConverter
    public static List<Ingredient> stringToIngredients(String value) {

        Type listType = new TypeToken<List<Ingredient>>() {}.getType();
        return new Gson().fromJson(value, listType);

    }

    @TypeConverter
    public static String ingredientsToString(List<Ingredient> ingredients) {

        Gson gson = new Gson();
        return gson.toJson(ingredients);


    }

    @TypeConverter
    public static List<Step> stringToSteps(String value) {

        Type listType = new TypeToken<List<Step>>() {}.getType();
        return new Gson().fromJson(value, listType);

    }

    @TypeConverter
    public static String stepsToString(List<Step> ingredients) {

        Gson gson = new Gson();
        return gson.toJson(ingredients);

    }


}
