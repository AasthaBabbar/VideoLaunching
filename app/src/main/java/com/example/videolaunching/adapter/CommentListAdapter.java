package com.example.videolaunching.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.videolaunching.R;
import com.example.videolaunching.model.Comment;
import java.util.List;

public class CommentListAdapter extends BaseAdapter {

    private Context contextView;
    private List<Comment> commentList;

    public CommentListAdapter(Context contextView, List<Comment> commentList) {
        this.contextView = contextView;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return commentList.get(position).getCommentId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View cntxtView = View.inflate(contextView, R.layout.fragment_slideshow, R.layout.);
        //cntxtView.findViewById(R.id.lstViewComments)
        LayoutInflater inflater = (LayoutInflater) contextView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.youtube_video_play, parent);
        TextView comment = (TextView)convertView.findViewById(R.id.txtViewComment);
        TextView comment2 = (TextView)convertView.findViewById(R.id.txtViewCommentarName);
        RatingBar rating = convertView.findViewById(R.id.rating);
        Comment comment1 = commentList.get(position);
        return convertView;
    }
}
