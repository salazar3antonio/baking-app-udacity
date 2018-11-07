package com.studentproject.bakingappudacity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studentproject.bakingappudacity.adapters.RecipeAdapter;
import com.studentproject.bakingappudacity.database.models.Recipe;

import java.util.List;

public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    private RecipeAdapter mRecipeAdapter;
    private RecyclerView mRecyclerView;

    public static MainActivityFragment newInstance() {
        return new MainActivityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecipeAdapter = new RecipeAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Inflate the Fragment View that holds the RecyclerView to display the list of Recipe Objects
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        boolean isSw600Dp = view.findViewById(R.id.ll_grid) != null;
        //this is the Recipe RecyclerView that inside of the Fragment View
        mRecyclerView = view.findViewById(R.id.rv_recipes);

        if (isSw600Dp) {
            //use GridLayout for tablets sw600dp
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        setupRecipeViewModel();

        return view;
    }

    public void setupRecipeViewModel() {

        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        recipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipeList) {
                mRecipeAdapter.setRecipes(recipeList);
                mRecyclerView.setAdapter(mRecipeAdapter);
            }
        });

    }

}
