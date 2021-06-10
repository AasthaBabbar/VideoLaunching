package com.example.videolaunching.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.videolaunching.model.Video;

import java.util.ArrayList;

public class VideoDBDAO {

    private DBHelper helper;
    private Context context;

    public VideoDBDAO(Context context){
        this.context = context;
        helper = DBHelper.getInstance(context);
        open();
    }

    public void open() throws SQLException {
        if(helper == null){
            helper = DBHelper.getInstance(context);
        }
        helper.getReadableDatabase();
    }

    public ArrayList<Video> getAllVideos(String subjectName){
        ArrayList<Video> list = helper.getAllVideosForSubject(subjectName);
        if(list == null)
            list = new ArrayList<>();
        return list;
    }
}
