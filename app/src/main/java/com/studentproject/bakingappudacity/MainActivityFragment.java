package com.studentproject.bakingappudacity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studentproject.bakingappudacity.adapters.RecipeAdapter;
import com.studentproject.bakingappudacity.data.Recipe;
import com.studentproject.bakingappudacity.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    public static MainActivityFragment newInstance() {
        return new MainActivityFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        List<Recipe> recipes = new ArrayList<>();

        try {
            recipes = JsonUtils.getRecipesFromJson(getContext());
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Recipes from JSON Error: ", e);
        }

        //Inflate the Fragment View that holds the RecyclerView to display the list of Recipe Objects
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        //this is the Recipe RecyclerView that inside of the Fragment View
        RecyclerView recyclerView = view.findViewById(R.id.rv_recipes);

        // TODO: 11/2/2018 handle landscape on tablet with a GridView

        //create adapter passing in our list of Recipes
        RecipeAdapter recipeAdapter = new RecipeAdapter(getContext(), recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recipeAdapter);

        return view;
    }
}
