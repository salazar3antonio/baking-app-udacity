package com.studentproject.bakingappudacity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.studentproject.bakingappudacity.database.RecipeDatabase;
import com.studentproject.bakingappudacity.database.models.Recipe;
import com.studentproject.bakingappudacity.utils.AppExecutors;
import com.studentproject.bakingappudacity.utils.JsonUtils;
import com.studentproject.bakingappudacity.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final String PREF_JSON_FETCH_SUCCESS_KEY = "pref_json_fetch_success_key";

    private SharedPreferences mSharedPreferences;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        mFragmentManager = getSupportFragmentManager();

        showProgressBarFragment();

        boolean hasInternet = NetworkUtils.isNetworkAvailableAndConnected(this);
        boolean jsonFetchSuccess = mSharedPreferences.getBoolean(PREF_JSON_FETCH_SUCCESS_KEY, false);

        if (hasInternet) {
            if (jsonFetchSuccess) {
                showRecipesFragment();
            } else {
                RecipeAsyncTask recipeAsyncTask = new RecipeAsyncTask();
                recipeAsyncTask.execute(NetworkUtils.buildJsonUrl());
            }
        } else {
            Toast.makeText(this, "No internet connection found.", Toast.LENGTH_LONG).show();
        }


    }

    public class RecipeAsyncTask extends AsyncTask<URL, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(URL... urls) {

            URL recipeJsonUrl = urls[0];
            String response;
            ArrayList<String> recipeJsonResults = new ArrayList<>();

            try {
                response = NetworkUtils.getResponseFromHttpUrl(recipeJsonUrl);
                recipeJsonResults.add(response);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return recipeJsonResults;
        }

        @Override
        protected void onPostExecute(ArrayList<String> recipeJsonResults) {

            List<Recipe> recipes = new ArrayList<>();

            try {
                recipes = JsonUtils.getRecipesFromJson(recipeJsonResults.get(0));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            mSharedPreferences.edit().putBoolean(PREF_JSON_FETCH_SUCCESS_KEY, true).apply();

            executeInsertRecipesToDb(recipes);
            showRecipesFragment();

        }

    }

    public void executeInsertRecipesToDb(final List<Recipe> listOfRecipes) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                RecipeDatabase recipe = RecipeDatabase.getsInstance(getApplicationContext());
                recipe.recipeDao().insertAllRecipes(listOfRecipes);
            }
        });


    }

    public void showRecipesFragment() {
        MainActivityFragment mainActivityFragment = MainActivityFragment.newInstance();
        mFragmentManager.beginTransaction().replace(R.id.activity_main_container, mainActivityFragment).commit();
    }

    public void showProgressBarFragment() {
        ProgressBarFragment progressBarFragment = ProgressBarFragment.newInstance();
        mFragmentManager.beginTransaction().add(R.id.activity_main_container, progressBarFragment).commit();
    }

}
