package com.studentproject.bakingappudacity.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;


import com.studentproject.bakingappudacity.database.models.Recipe;

@Database(entities = Recipe.class, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {

    private static final String TAG = RecipeDatabase.class.getName();

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "recipes_db";
    private static RecipeDatabase sInstance;

    //singleton to get an instance of the database
    public static RecipeDatabase getsInstance(Context context) {

        if (sInstance == null) {
            synchronized (LOCK) {
                Log.i(TAG, "Recipe DB created");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        RecipeDatabase.class, RecipeDatabase.DATABASE_NAME)
                        .build();
            }
        }

        return sInstance;
    }

    public abstract RecipeDao movieDao();



}
