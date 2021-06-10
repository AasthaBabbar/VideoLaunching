package com.example.videolaunching.ui.home;

import android.content.Context;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.videolaunching.adapter.SubjectListAdapter;
import com.example.videolaunching.R;
import com.example.videolaunching.model.Subject;
import com.example.videolaunching.ui.gallery.GalleryFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    com.example.videolaunching.adapter.SubjectListAdapter subjectListAdapter;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = getViewModelStore().getClass().
        //ViewModelProviders.of(this).get(HomeViewModel.class);
        Context context = getContext();

        homeViewModel = new HomeViewModel(context);//ViewModelProviders.of(this).get(HomeViewModel.class);//new HomeViewModel(getContext());
        final ArrayList<String> list = homeViewModel.getSubjectList();
        subjectListAdapter = new SubjectListAdapter(context, list);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        listView = root.findViewById(R.id.subjectList);
        listView.setAdapter(subjectListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("subject", list.get(position));
                GalleryFragment fragment = new GalleryFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
            }
        });
        return root;
    }

}