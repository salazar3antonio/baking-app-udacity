package com.studentproject.bakingappudacity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.studentproject.bakingappudacity.adapters.RecipeAdapter;
import com.studentproject.bakingappudacity.adapters.StepAdapter;
import com.studentproject.bakingappudacity.data.Ingredient;
import com.studentproject.bakingappudacity.data.Recipe;
import com.studentproject.bakingappudacity.data.Step;

import java.util.List;

public class RecipeDetailsFragment extends Fragment {

    private Recipe mRecipe;
    private TextView mIngredientsList;
    private RecyclerView mStepsRecyclerView;


    public static RecipeDetailsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the Arguments stored in the Bundle that is set to this Fragment.
        savedInstanceState = getArguments();
        //check for null
        if (savedInstanceState != null) {
            //get the Recipe (Parcelable) Object from the Bundle and assign it to mRecipe
            mRecipe = savedInstanceState.getParcelable(RecipeAdapter.RECIPE_EXTRA);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View recipeView = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        mIngredientsList = recipeView.findViewById(R.id.tv_ingredients_list);
        mStepsRecyclerView = recipeView.findViewById(R.id.rv_steps);

        List<Ingredient> ingredients = mRecipe.getIngredients();
        List<Step> steps = mRecipe.getSteps();

        for (Ingredient ingredient : ingredients) {

            int quantity = ingredient.getQuantity();
            String measure = ingredient.getMeasure();
            String ingredientName = ingredient.getIngredient();

            mIngredientsList.append(quantity + " " + measure + " " + ingredientName + "\n");

        }

        StepAdapter stepAdapter = new StepAdapter(getContext(), steps);
        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStepsRecyclerView.setAdapter(stepAdapter);
        
        return recipeView;
    }
}
