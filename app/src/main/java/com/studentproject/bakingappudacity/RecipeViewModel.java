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
        Log.i(TAG, "Actively retrieving the tasks from the DataBase");
        allRecipes = recipeDatabase.movieDao().allRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }


}
