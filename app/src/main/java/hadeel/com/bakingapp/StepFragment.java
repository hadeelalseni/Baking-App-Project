package hadeel.com.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import hadeel.com.bakingapp.Model.Recipe;
import hadeel.com.bakingapp.Model.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StepFragment extends Fragment {
    private Steps step;
    private SimpleExoPlayer exoPlayer;
    private long position;
    private TrackSelector trackSelector;
    SimpleExoPlayerView exoPlayerView;
    public StepFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step = (Steps) getArguments().getSerializable("oneStep");
        if (savedInstanceState != null){
            position = savedInstanceState.getLong("Position");

        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle b){
        super.onSaveInstanceState(b);
        b.putLong("Position", position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step, container, false);
        PlayerView playerView = view.findViewById(R.id.exo_player_view);
        ImageView noVideoImg = view.findViewById(R.id.step_iv);

        Bundle b = this.getArguments();
        playerView.setVisibility(View.GONE);

        if(b != null){
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            //TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            //TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
            System.out.println("URL IS : "+ step.getVideoURL());
            Uri uri = Uri.parse(step.getVideoURL());
            DefaultHttpDataSourceFactory defaultHttpDataSourceFactory =
                    new DefaultHttpDataSourceFactory("exo_step");

            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(uri, defaultHttpDataSourceFactory, extractorsFactory, null, null);

            playerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

            if(position > 0){
                exoPlayer.seekTo(position);
            }
            playerView.setVisibility(View.VISIBLE);
            if(step.getVideoURL().equals("")){
                noVideoImg.setVisibility(View.VISIBLE);
                playerView.setVisibility(View.GONE);
                Picasso.get().load(Uri.parse(step.getThumbnailURL())).placeholder(R.drawable.exo_icon_stop)
                        .error(R.drawable.exo_icon_stop).into(noVideoImg);
            }

        }
        return view;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        exitPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        position = exoPlayer != null ? exoPlayer.getCurrentPosition(): 0;
        if(Util.SDK_INT <= 23){
            exitPlayer();
        }
    }
    @Override
    public void onStop() {

        super.onStop();
        if(Util.SDK_INT > 23){
            exitPlayer();
        }
    }
    public void exitPlayer(){
        if(exoPlayer != null){
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
            trackSelector = null;
        }
    }

    public static StepFragment newInstance(Steps step) {
        StepFragment stepFragment= new StepFragment();
        Bundle args = new Bundle();
        args.putSerializable("oneStep", step);
        stepFragment.setArguments(args);
        return stepFragment;
    }
}
