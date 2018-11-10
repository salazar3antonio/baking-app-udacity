package com.studentproject.bakingappudacity.view_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.CheckBox;

import com.studentproject.bakingappudacity.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientViewHolder extends ViewHolder {

    public @BindView(R.id.cb_ingredient) CheckBox mIngredientCheckBox;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

    }


}
