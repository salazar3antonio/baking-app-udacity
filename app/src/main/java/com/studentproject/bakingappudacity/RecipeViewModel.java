package com.studentproject.bakingappudacity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.util.Log;

import com.studentproject.bakingappudacity.database.RecipeDatabase;
import com.studentproject.bakingappudacity.database.models.Recipe;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private static final String TAG = RecipeViewModel.class.getName();

    private LiveData<List<Recipe>> allRecipes;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        // get instance of the DB
        RecipeDatabase recipeDatabase = RecipeDatabase.getsInstance(application.getApplicationContext());
        //my quantities are being rounded. for instance Nutella Pie > Ingredient has 0.5 as a quantity, but when I query all Recipes, the Ingredient quantity comes out of the DB as 0.0.
        //i'm storing double data types in the DB but fetching them is returning them rounded down :(
        allRecipes = recipeDatabase.recipeDao().allRecipesAsLiveData();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }


}
