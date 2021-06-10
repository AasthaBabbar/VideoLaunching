package com.example.videolaunching.ui.slideshow;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.videolaunching.MainActivity;
import com.example.videolaunching.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class SlideshowFragment extends YouTubePlayerFragment implements View.OnClickListener {

    YouTubePlayerView videoPlayer;
    YouTubePlayer.OnInitializedListener OnInitializedListener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            youTubePlayer.loadVideo("DoH2fsOxbuE");
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            //Toast.makeText(, "error occurred", Toast.LENGTH_LONG).show();
        }
    };
   // MediaController controller;
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //slideshowViewModel =
                //ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        videoPlayer = root.findViewById(R.id.videoPlayer);
        videoPlayer.initialize("AIzaSyCzztnsz3OmZAwRQAHeUrtYh6yaLxvw-Sk", OnInitializedListener);

        //videoPlayer.initialize("AIzaSyCzztnsz3OmZAwRQAHeUrtYh6yaLxvw-Sk", OnInitializedListener);
        return root;
    }

    @Override
    public void onClick(View v) {

    }
}
