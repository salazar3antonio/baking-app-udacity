package com.studentproject.bakingappudacity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studentproject.bakingappudacity.adapters.IngredientAdapter;
import com.studentproject.bakingappudacity.adapters.RecipeAdapter;
import com.studentproject.bakingappudacity.adapters.StepAdapter;
import com.studentproject.bakingappudacity.database.models.Ingredient;
import com.studentproject.bakingappudacity.database.models.Recipe;
import com.studentproject.bakingappudacity.database.models.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.studentproject.bakingappudacity.StepDetailsDialogFragment.STEP_DETAILS_DIALOG_FRAGMENT_TAG;

public class RecipeDetailsFragment extends Fragment {

    public static final String RECIPE_DETAILS_FRAGMENT_TAG = "recipe_details_fragment_tag";

    private Recipe mRecipe;
    @BindView(R.id.rv_steps)
    RecyclerView mStepsRecyclerView;
    @BindView(R.id.rv_ingredients)
    RecyclerView mIngredientsRecyclerView;


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

        ButterKnife.bind(this, recipeView);

        List<Ingredient> ingredients = mRecipe.getIngredients();
        List<Step> steps = mRecipe.getSteps();

        IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), ingredients);
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mIngredientsRecyclerView.setAdapter(ingredientAdapter);

        StepAdapter stepAdapter = new StepAdapter(getContext(), steps);
        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStepsRecyclerView.setAdapter(stepAdapter);

        return recipeView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Fragment stepDetailsDialogFragment = getFragmentManager().findFragmentByTag(STEP_DETAILS_DIALOG_FRAGMENT_TAG);

        //this attaches the AddTagsToArticleFragment upon a config change
        if (stepDetailsDialogFragment != null) {
            stepDetailsDialogFragment.setTargetFragment(this, 1);
        }

    }

}
