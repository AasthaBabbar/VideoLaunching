package com.example.videolaunching.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.videolaunching.R;
import com.example.videolaunching.model.Subject;
import com.example.videolaunching.model.Video;

import java.util.ArrayList;

public class VideoListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Video> videoList;

    public VideoListAdapter(Context context, ArrayList<Video> list){
        this.context =  context;
        videoList = list;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Video getItem(int position) {
        return videoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Video video  = getItem(position);
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.videolist_item, parent, false);
        TextView video = (TextView)convertView.findViewById(R.id.txtViewVideoName);
        video.setText(videoList.get(position).getVideoDescription());
        Video video1 = videoList.get(position);
        return convertView;
    }
}
