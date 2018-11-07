package com.studentproject.bakingappudacity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.studentproject.bakingappudacity.database.RecipeDatabase;
import com.studentproject.bakingappudacity.database.models.Ingredient;
import com.studentproject.bakingappudacity.database.models.Recipe;
import com.studentproject.bakingappudacity.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class StackViewWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackViewsRemoteViewsFactory(this.getApplicationContext(), intent);
    }

}


class StackViewsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = StackViewWidgetService.class.getSimpleName();
    //this class will bind data to one Recipe widget view. StackView is the container for each Recipe Widget Views
    private Context mContext;
    private List<Recipe> mRecipes = new ArrayList<>();
    private Intent mIntent;

    public StackViewsRemoteViewsFactory(Context context, Intent intent) {
        this.mContext = context;
        this.mIntent = intent;
    }

    @Override
    public void onCreate() {

        Log.i(TAG, "onCreate: ");

    }

    @Override
    public void onDataSetChanged() {

        RecipeDatabase recipeDatabase = RecipeDatabase.getsInstance(mContext);
        mRecipes = recipeDatabase.recipeDao().allRecipesAsList();
        Log.i(TAG, "onDataSetChanged: " + mRecipes.size());

    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        mRecipes.clear();
    }

    @Override
    public int getCount() {
        if (mRecipes.size() == 0) return 0;
        Log.i(TAG, "getCount: ");
        return mRecipes.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        Log.i(TAG, "getViewAt: ");

        if (mRecipes == null || mRecipes.size() == 0) return null;

        Recipe recipe = mRecipes.get(position);
        List<Ingredient> ingredients = recipe.getIngredients();
        String ingredientFullString;

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_recipe);

        // TODO: 11/6/2018 insert all ingredients into Recipe Widget
        for (Ingredient ingredient : ingredients) {
            String ingredientFullIngredient = ingredient.getFullIngredient();
            remoteViews.setTextViewText(R.id.tv_widget_ingredients, ingredientFullIngredient);
        }
        remoteViews.setTextViewText(R.id.tv_widget_recipe_name, recipe.getName());

        return remoteViews;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
