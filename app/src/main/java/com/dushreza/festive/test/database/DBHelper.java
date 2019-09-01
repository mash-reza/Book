package com.dushreza.festive.test.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    private SQLiteDatabase database;
    private Context context;
    private static final String DATABASE_NAME = "Book.db";
    private static String DATABASE_PATH;
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
//        DATABASE_PATH = context.getFilesDir().getParent()+ "/databases/" + DATABASE_NAME;
//        DATABASE_PATH = "/data/user/0/com.dushreza.festive.test/databases/Book.db";
        //Log.i(TAG, "DBHelper: " + DATABASE_PATH);
    }

    public void createDataBase() {
        if (checkDataBase()) {

            Log.i(TAG, "no databases");
        } else {
            Log.i(TAG, "DataBase is getting copied");
            copyDataBase();
        }
//        if (checkDataBase()) {
//            //onUpgrade(databases, DATABASE_VERSION, DATABASE_VERSION);
//            copyDataBase();
//            System.out.println("databases exists");
//        }else {
//            System.out.println("databases does not exist");
//            this.getReadableDatabase();
//            this.close();
//            copyDataBase();
//        }
    }

    public void openDataBase() {
        if (checkDataBase()) {
            database = SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        } else {
            Log.i(TAG, "openDataBase: "+DATABASE_PATH);
            copyDataBase();
            openDataBase();

        }

    }

//    private synchronized void closeDataBase() {
//        if (databases != null) databases.close();
//        super.close();
//    }

    private boolean checkDataBase() throws SQLException {
//        File file = new File(DATABASE_PATH);
//        return file.exists();
        boolean flag;
        SQLiteDatabase databases = SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        flag = databases != null;
        return flag;
    }

    private void copyDataBase() {
        try {
            File databaseFilePath = context.getDatabasePath(DATABASE_NAME);
            File outFile = new File(databaseFilePath.getParent());
            if (!outFile.exists()) {
                outFile.mkdirs();
            }

            OutputStream myOutput = new FileOutputStream(databaseFilePath);


            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = inputStream.read(mBuffer)) > 0) {
                myOutput.write(mBuffer, 0, mLength);
            }
            //databases = o
            inputStream.close();
            myOutput.flush();
            myOutput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
             createDataBase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
