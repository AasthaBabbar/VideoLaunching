package com.example.videolaunching.ui.gallery;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.videolaunching.database.VideoDBDAO;
import com.example.videolaunching.model.Video;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    private ArrayList<Video> videoList;
    private VideoDBDAO videoDAO;


    public GalleryViewModel(Context context) {
        videoDAO = new VideoDBDAO(context);
        videoList = new ArrayList<>();

    }

    public ArrayList<Video> getVideoList(String subjectName) {
        videoList = videoDAO.getAllVideos(subjectName);
        return videoList;
    }
}