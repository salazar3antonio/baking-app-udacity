package com.studentproject.bakingappudacity.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studentproject.bakingappudacity.R;
import com.studentproject.bakingappudacity.RecipeDetailsActivity;
import com.studentproject.bakingappudacity.database.models.Recipe;
import com.studentproject.bakingappudacity.view_holders.RecipeViewHolder;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    public static final String RECIPE_EXTRA = "recipe_extra";

    private List<Recipe> mRecipes;

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recipe, viewGroup, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder recipeViewHolder, int i) {

        final Recipe recipe = mRecipes.get(i);

        recipeViewHolder.mRecipeName.setText(recipe.getName());

        recipeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //launch RecipeDetailsActivity via an Intent passing in the Recipe object as a Bundle
                Intent intent = new Intent(view.getContext(), RecipeDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(RECIPE_EXTRA, recipe);
                intent.putExtras(bundle);
                //start activity
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

}
