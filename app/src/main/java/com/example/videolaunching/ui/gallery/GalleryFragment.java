package com.example.videolaunching.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.videolaunching.R;
import com.example.videolaunching.adapter.VideoListAdapter;
import com.example.videolaunching.model.Video;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private VideoListAdapter videoListAdapter;
    private ListView listView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new GalleryViewModel(getContext());

        final ArrayList<Video> videoArrayList = galleryViewModel.getVideoList(getArguments().get("subject").toString());
        videoListAdapter = new VideoListAdapter(getContext(), videoArrayList);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        listView = root.findViewById(R.id.listView_links);
        listView.setAdapter(videoListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("video", videoArrayList.get(position).getVideoDescription());
                //GalleryFragment fragment = new GalleryFragment();
                //fragment.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
            }
        });

        return root;
    }
}
