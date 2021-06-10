package com.example.videolaunching.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.videolaunching.model.Subject;

import java.util.ArrayList;

public class SubjectDBDAO {

    private DBHelper helper;
    private Context context;

    public SubjectDBDAO(Context context){
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

    public ArrayList<String> GetAllSubjects(){
        ArrayList<String> list;

        list = helper.getAllSubjectNames();

        if(list == null){
            list = new ArrayList<>();
        }
        return list;
    }
}
