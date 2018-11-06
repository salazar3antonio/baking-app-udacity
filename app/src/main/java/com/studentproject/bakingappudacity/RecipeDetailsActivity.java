package com.studentproject.bakingappudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.studentproject.bakingappudacity.adapters.RecipeAdapter;
import com.studentproject.bakingappudacity.adapters.StepAdapter;
import com.studentproject.bakingappudacity.database.models.Recipe;
import com.studentproject.bakingappudacity.database.models.Step;

public class RecipeDetailsActivity extends AppCompatActivity implements StepAdapter.OnStepClickListener {

    public static final String STEP_EXTRA = "step_id_extra";

    private FragmentManager mFragmentManager;

    private boolean mIsTwoPane;
    private Recipe mRecipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //receive the Recipe Object data from the Intent Extras that launched this activity
        //create bundle and pass to the RecipeDetailsFragment.newInstance(BUNDLE)

        mIsTwoPane = findViewById(R.id.ll_recipe_details) != null;

        RecipeDetailsFragment recipeDetails = RecipeDetailsFragment.newInstance();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            mRecipe = bundle.getParcelable(RecipeAdapter.RECIPE_EXTRA);
            actionBar.setTitle(mRecipe.getName());
        }

        recipeDetails.setArguments(bundle);

        //here we will host the RecipeDetailsFragment
        mFragmentManager = getSupportFragmentManager();
        //pass the Recipe object as a bundle to the fragment
        mFragmentManager.beginTransaction().replace(R.id.fl_recipe_container, recipeDetails).commit();

    }

    @Override
    public void onStepClicked(Step step) {

        //new StepDetailsFragment with the stepId stored as a Bundle. This way the fragment know which fragment to go to.
        StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putParcelable(STEP_EXTRA, step);
        stepDetailsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (mIsTwoPane) {
            fragmentTransaction.replace(R.id.fl_step_container, stepDetailsFragment);
        } else {
            //this is where we will inflate the new StepDetailsFragment
            fragmentTransaction.replace(R.id.fl_recipe_container, stepDetailsFragment);
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


}
