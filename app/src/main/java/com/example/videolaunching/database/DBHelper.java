package com.example.videolaunching.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.videolaunching.model.Comment;
import com.example.videolaunching.model.Subject;
import com.example.videolaunching.model.Video;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DATABASE HELPER CLASS*/
public class DBHelper extends SQLiteOpenHelper{

    // region CONSTANTS DEFINED

    private static final String DB_NAME = "SubjectVideo.sqlite";
    private static final String COL_SUBJECTNAME = "subjectName";
    private static final String COL_SUBJECTID = "subjectId";
    private static final String COL_VIDEOID = "videoId";
    private static final String COL_VIDEONAME = "videoName";
    private static final String COL_COMMENTID = "commentId";
    private static final String COL_COMMENT = "comment";
    private static final String COL_COMMENTARNAME = "commentarName";
    private static final String COL_RATING = "rating";
    private static  String DB_LOCATION = "data/data/com.example.videolaunching/databases/";
    private static final String DB_VIDEO_TABLE = "video";
    private static final String DB_SUBJECT_TABLE = "subject";
    private static final String DB_COMMENT_TABLE = "comment";
    private static final String CREATE_SUBJECT_TABLE="CREATE TABLE "+ DB_SUBJECT_TABLE+ " (\n" +
            "\t"+ COL_SUBJECTID+"\tTEXT NOT NULL,\n" +
            "\t"+ COL_SUBJECTNAME +"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY( "+COL_SUBJECTID+" )\n" +
            ")\n";
    private static final String CREATE_VIDEO_TABLE = "\n" +
            "CREATE TABLE "+ DB_VIDEO_TABLE+" (\n" +
            "\t"+ COL_VIDEOID+"\tTEXT NOT NULL,\n" +
            "\t"+COL_VIDEONAME+"\tTEXT NOT NULL,\n" +
            "\t"+COL_SUBJECTID+"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY( "+COL_VIDEOID+" ),\n" +
            "\tFOREIGN KEY( "+COL_SUBJECTID+" ) REFERENCES "+DB_SUBJECT_TABLE+"( "+COL_SUBJECTID+" )\n" +
            ")\n";

    private static final String CREATE_COMMENT_TABLE = "CREATE TABLE "+ DB_COMMENT_TABLE+" (\n" +
            "\t"+ COL_COMMENTID+"\tINTEGER NOT NULL,\n" +
            "\t"+COL_COMMENT+"\tTEXT,\n" +
            "\t"+COL_COMMENTARNAME+"\tTEXT NOT NULL,\n" +
            "\t"+COL_RATING+"\tNUMERIC,\n" +
            "\t"+COL_VIDEOID+"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY( "+COL_COMMENTID+" ),\n" +
            "\tFOREIGN KEY( "+COL_VIDEOID+" ) REFERENCES "+DB_VIDEO_TABLE+"( "+COL_VIDEOID+" )\n" +
            ")\n";

    // endregion

    // region private members
    private  static DBHelper instance;
    private Context contextView;
    private SQLiteDatabase database;

    // endregion

    // Done
    public static synchronized DBHelper getInstance(Context context){
        if (instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.contextView = context;
        DB_LOCATION = context.getDatabasePath(DB_NAME).getPath();
    }

    private void CreateDatabase() throws IOException{
        if(!checkDatabase()){
            this.getReadableDatabase();
            copyDatabase();
            this.close();
        }
    }

    private boolean checkDatabase(){
        File file = new File(DB_LOCATION + DB_NAME);
        return file.exists();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //File file = new File(DB_LOCATION + DB_NAME);

        //if(!file.exists())
        //{
        //    if (!CopyDatabase(contextView)){
        //        db.execSQL(CREATE_SUBJECT_TABLE);
        //        db.execSQL(CREATE_VIDEO_TABLE);
        //        db.execSQL(CREATE_COMMENT_TABLE);
        //    }
        //}
    }

    private boolean copyDatabase() throws IOException{
        try{

            //if(alist.contains(DB_NAME)) {
                InputStream inputStream = contextView.getAssets().open(DB_NAME);
                OutputStream outputStream = new FileOutputStream(DB_LOCATION + DB_NAME);
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer);
                }
                outputStream.flush();
                outputStream.close();
                return true;
            //}

        }catch(Exception e){
                e.printStackTrace();
                return false;
        }
        //return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }


    public void openDatabase() throws SQLException {
        String dbPath = contextView.getDatabasePath(DB_NAME).getPath();
        if(database != null && database.isOpen()) {
            return;
        }
        //else if (database == null) {
        //    if (CopyDatabase(contextView)){
        //        return;
        //    }
        database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }



    public synchronized void closeDatabase(){
        if(database != null){
            database.close();
        }
        SQLiteDatabase.releaseMemory();
        super.close();
    }

    public ArrayList<String> getAllSubjectNames(){
        ArrayList<String> list = new ArrayList<>();
        openDatabase();
        Cursor c = database.rawQuery("select video." + COL_SUBJECTID + ", subject." + COL_SUBJECTNAME + " from subject \n" +
                "join  video\n" +
                " on subject." + COL_SUBJECTID + " = video." + COL_SUBJECTID +" group by subject. " + COL_SUBJECTID, null);//query("subject", subjectColArray,null,null,null,null,null);
        if(c != null){
            try{
                int count = 0;
                int num = c.getCount();
                    if(c.moveToFirst()) {
                        do  {
                            //list.add(c.getColumnName(count));
                            final String subjectName = c.getString(c.getColumnIndex(COL_SUBJECTNAME));
                            list.add(subjectName);
                            count++;
                        } while (c.moveToNext());
                    }
            }catch(Exception e){
                e.printStackTrace();
            }
            finally {
                c.close();
            }
        }
        closeDatabase();
        return list;
    }

    public ArrayList<Video> getAllVideosForSubject(String subjectName){
        ArrayList<Video> list = new ArrayList<>();
        openDatabase();
        Cursor c = database.rawQuery("select " + COL_VIDEOID + ", " + COL_VIDEONAME + ", " + COL_SUBJECTID + " from video \n" +
                "where  \n" + COL_SUBJECTID +" = " +
                " (select " + COL_SUBJECTID + " from subject where " + COL_SUBJECTNAME + " = \'" + subjectName + "\')", null);//query("subject", subjectColArray,null,null,null,null,null);
        if(c != null){
            try{
                int count = 0;
                c.moveToFirst();
                Video vid;
                do{
                    final String videoId = c.getString(c.getColumnIndex(COL_VIDEOID));
                    final String videoName = c.getString(c.getColumnIndex(COL_VIDEONAME));
                    final String subjectId = c.getString(c.getColumnIndex(COL_SUBJECTID));
                    vid = new Video(videoId, videoName, subjectId);
                    list.add(vid);
                    count++;
                }while(c.moveToNext());
            }
            finally {
                c.close();
            }
            closeDatabase();
        }
        return list;
    }

    public ArrayList<Comment> getAllCommentsForVideo(String videoId) {
        ArrayList<Comment> list = new ArrayList<>();

        //String[] subjectColArray = {DBHelper.COL_SUBJECTID, DBHelper.COL_SUBJECTNAME};
        Cursor c = database.rawQuery("select * from "+ DB_COMMENT_TABLE+" \n" +
                "where  \n" + COL_VIDEOID +" = " + videoId + " ;", null);

        if(c != null){
            try{
                int count = 0;
                c.moveToFirst();
                Comment comment;
                while(c.moveToNext()){
                    String[] columnNames = c.getColumnNames();
                    comment = new Comment(Integer.parseInt(columnNames[0]), columnNames[1], columnNames[2], Double.parseDouble(columnNames[3]), videoId);
                    list.add(comment);
                    count++;
                }
            }
            finally {
                c.close();
            }
        }
        return list;
    }

    public void AddComment(Comment comment){
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            //long userId = addOrUpdateUser(post.user);

            ContentValues values = new ContentValues();
            values.put(COL_COMMENT, comment.getCommentDescription());
            values.put(COL_COMMENTARNAME, comment.getCommentarName());
            values.put(COL_RATING, comment.getRating());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(DB_COMMENT_TABLE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("Error", "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }
}
