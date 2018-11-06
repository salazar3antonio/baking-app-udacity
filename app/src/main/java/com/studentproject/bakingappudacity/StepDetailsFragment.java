package com.studentproject.bakingappudacity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.studentproject.bakingappudacity.database.models.Step;

import static com.studentproject.bakingappudacity.RecipeDetailsActivity.*;

public class StepDetailsFragment extends Fragment {

    private static final String CURRENT_POSITION = "current_player_position";

    //this Fragment will hold the Step details. Includes the video and Step descriptions.

    private Step mStep;
    private TextView mStepDesc;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mExoPlayerView;
    private long mCurrentPlayerPosition;

    public static StepDetailsFragment newInstance() {

        Bundle args = new Bundle();

        StepDetailsFragment fragment = new StepDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        savedInstanceState = getArguments();

        if (savedInstanceState != null) {
            mStep = savedInstanceState.getParcelable(STEP_EXTRA);
            mCurrentPlayerPosition = savedInstanceState.getLong(CURRENT_POSITION);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View stepView = inflater.inflate(R.layout.fragment_step_details, container, false);

       initExoPlayer(Uri.parse(mStep.getVideoUrl()), stepView);

        mStepDesc = stepView.findViewById(R.id.tv_step_description);
        mStepDesc.setText(mStep.getDescription());

        return stepView;
    }



    public void initExoPlayer(Uri videoUri, View view) {

        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
        mExoPlayerView = view.findViewById(R.id.epv_step_video);
        mExoPlayerView.setControllerShowTimeoutMs(1000);
        mExoPlayerView.setPlayer(mExoPlayer);

        String userAgent = Util.getUserAgent(getContext(), "BakingAppUdacity");
        MediaSource videoSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(getContext(), userAgent),
                new DefaultExtractorsFactory(), null, null);

        mExoPlayer.prepare(videoSource);
        if (mCurrentPlayerPosition == 0) {
            mExoPlayer.seekTo(C.TIME_UNSET);
        } else {
            mExoPlayer.seekTo(mCurrentPlayerPosition);
        }
        mExoPlayer.setPlayWhenReady(true);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        mCurrentPlayerPosition = mExoPlayer.getCurrentPosition();
        outState.putLong(CURRENT_POSITION, mCurrentPlayerPosition);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mExoPlayer.release();
    }
}
