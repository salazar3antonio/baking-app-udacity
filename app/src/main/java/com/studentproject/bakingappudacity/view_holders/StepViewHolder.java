package com.studentproject.bakingappudacity.view_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.studentproject.bakingappudacity.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepViewHolder extends RecyclerView.ViewHolder {

    public @BindView(R.id.tv_step_short_desc) TextView mStepShortDesc;

    public StepViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

    }


}
