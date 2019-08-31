package com.dushreza.festive.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    private Context context;
    private static final String DATABASE_NAME = "Book.db";
    private final static String DATABASE_PATH = "../assets/database/";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    private void createDataBase() {
        if (checkDataBase()) {
            onUpgrade(database, DATABASE_VERSION, DATABASE_VERSION + 1);
        }else {
            this.getReadableDatabase();
            this.close();
            copyDataBase();
        }
    }

    private void openDataBase() {
        database = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private synchronized void closeDataBase() {
        if (database != null) database.close();
        super.close();
    }

    private boolean checkDataBase() {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        return file.exists();
    }

    private void deleteDataBase() {

        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if (file.exists())
            file.delete();
    }

    private void copyDataBase() {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String fileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(fileName);
            byte[] mBuffer = new byte[2024];
            int mLength;
            while ((mLength = inputStream.read(mBuffer)) > 0) {
                outputStream.write(mBuffer, 0, mLength);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
