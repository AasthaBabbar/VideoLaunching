package com.example.videolaunching.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentDBDAO {
    protected SQLiteDatabase database;
    private DBHelper helper;
    private Context context;

    public CommentDBDAO(Context context){
        this.context = context;
        helper = DBHelper.getInstance(context);
        open();
    }

    public void open() throws SQLException {
        if(helper == null){
            helper = DBHelper.getInstance(context);
        }
        helper.getWritableDatabase();
    }
}
