package com.example.book.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.book.model.Chapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Sqlite extends SQLiteOpenHelper {

    public static final String DATABASE_BOOK_NAME = "Book.db";

    private SQLiteDatabase database;
    private Context context;

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public Sqlite(Context context) {
        super(context, DATABASE_BOOK_NAME, null, 1);
        this.context = context;
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase database = null;
        try {
            String myDatabase = DATABASE_BOOK_NAME;
            database = SQLiteDatabase.openDatabase(myDatabase, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (database != null)
            database.close();

        return database != null;
    }

    private void copyDatabase() throws IOException {
        InputStream myInput = context.getAssets().open(DATABASE_BOOK_NAME);
        File outFile = context.getDatabasePath(DATABASE_BOOK_NAME);
        String outFileName = outFile.getPath();
        OutputStream myOutPut = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutPut.write(buffer, 0, length);
        }
        myOutPut.flush();
        myOutPut.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        File outFile = context.getDatabasePath(DATABASE_BOOK_NAME);
        String myPath = outFile.getPath();
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public List<Chapter> getChapters() {
        List<Chapter> chapters = new ArrayList<>();

//        database = this.getReadableDatabase();
//        Cursor cursor = database.query("chapter",
//                null, null, null, null, null, null);


        Cursor cursor = database.rawQuery("select * from chapter", null);
        while (cursor.moveToNext()) {
//            Log.d("SQTESTTAG", "Read: " + cursor.getString(cursor.getColumnIndexOrThrow("title")));
            chapters.add(new Chapter(cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("content")),
                    cursor.getString(cursor.getColumnIndexOrThrow("image"))));
        }
        cursor.close();
        return chapters;
    }


}
