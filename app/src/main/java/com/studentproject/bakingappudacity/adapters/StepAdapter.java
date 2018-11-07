package com.studentproject.bakingappudacity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studentproject.bakingappudacity.R;
import com.studentproject.bakingappudacity.database.models.Step;
import com.studentproject.bakingappudacity.view_holders.StepViewHolder;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepViewHolder> {

    private List<Step> mSteps;
    private Context mContext;
    private OnStepClickListener mStepClickCallback;

    public StepAdapter(Context context, List<Step> steps) {
        this.mSteps = steps;
        this.mContext = context;
    }

    public interface OnStepClickListener {
        //interface method.
        void onStepClicked(Step step);
    }



    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_step, viewGroup, false);

        //assign the callback to the context that was passed in
        mStepClickCallback = (OnStepClickListener) mContext;

        return new StepViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder stepViewHolder, int position) {

        final Step step = mSteps.get(position);

        stepViewHolder.mStepShortDesc.setText(step.getShortDescription());

        stepViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStepClickCallback.onStepClicked(step);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public void setSteps(List<Step> steps) {
        mSteps = steps;
        notifyDataSetChanged();
    }
}
