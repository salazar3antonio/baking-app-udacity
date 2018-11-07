package com.studentproject.bakingappudacity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studentproject.bakingappudacity.R;
import com.studentproject.bakingappudacity.database.models.Ingredient;
import com.studentproject.bakingappudacity.view_holders.IngredientViewHolder;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientViewHolder> {

    private List<Ingredient> mIngredients;
    private Context mContext;

    public IngredientAdapter(Context context, List<Ingredient> ingredients) {
        this.mIngredients = ingredients;
        this.mContext = context;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_ingredient, viewGroup, false);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder ingredientViewHolder, int i) {

        Ingredient ingredient = mIngredients.get(i);
        ingredientViewHolder.mIngredientCheckBox.setText(ingredient.getFullIngredient());

    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }
}
