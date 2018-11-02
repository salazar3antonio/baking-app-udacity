package com.studentproject.bakingappudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;
import android.widget.Toolbar;

import com.studentproject.bakingappudacity.adapters.StepAdapter;
import com.studentproject.bakingappudacity.data.Step;

public class RecipeDetailsActivity extends AppCompatActivity implements StepAdapter.OnStepClickListener {

    public static final String STEP_EXTRA = "step_id_extra";
    public static final String STEP_FRAGMENT_TAG = "step_fragment_tag";

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //receive the Recipe Object data from the Intent Extras that launched this activity
        //create bundle and pass to the RecipeDetailsFragment.newInstance(BUNDLE)

        RecipeDetailsFragment recipeDetails = RecipeDetailsFragment.newInstance();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        recipeDetails.setArguments(bundle);

        //here we will host the RecipeDetailsFragment
        mFragmentManager = getSupportFragmentManager();
        //pass the Recipe object as a bundle to the fragment
        mFragmentManager.beginTransaction().replace(R.id.fl_recipe_detials, recipeDetails).commit();

    }


    @Override
    public void onStepClicked(Step step) {

        //new StepDetailsFragment with the stepId stored as a Bundle. This way the fragment know which fragment to go to.
        StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putParcelable(STEP_EXTRA, step);
        stepDetailsFragment.setArguments(bundle);

        //this is where we will inflate the new StepDetailsFragment
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_recipe_detials, stepDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }



}
