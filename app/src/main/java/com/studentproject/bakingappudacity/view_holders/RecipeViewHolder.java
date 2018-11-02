package com.studentproject.bakingappudacity.view_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.studentproject.bakingappudacity.R;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    public TextView mRecipeName;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        mRecipeName = itemView.findViewById(R.id.tv_recipe_name);

    }



}
