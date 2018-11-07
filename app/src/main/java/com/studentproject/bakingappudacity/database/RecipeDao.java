package com.studentproject.bakingappudacity.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.studentproject.bakingappudacity.database.models.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes_table")
    LiveData<List<Recipe>> allRecipesAsLiveData();

    @Query("SELECT * FROM recipes_table")
    List<Recipe> allRecipesAsList();

    @Insert
    void insertAllRecipes(List<Recipe> recipeList);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecipes(Recipe recipe);

}
