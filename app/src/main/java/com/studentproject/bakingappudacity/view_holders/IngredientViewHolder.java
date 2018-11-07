package com.studentproject.bakingappudacity.view_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.CheckBox;

import com.studentproject.bakingappudacity.R;

public class IngredientViewHolder extends ViewHolder {

    public CheckBox mIngredientCheckBox;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);

        mIngredientCheckBox = itemView.findViewById(R.id.cb_ingredient);

    }


}
