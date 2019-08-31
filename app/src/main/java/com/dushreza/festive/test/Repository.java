package com.dushreza.festive.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Context context;

    public Repository(Context context) {
        this.context = context;
    }

    public static List<Chapter> getChapters() {
        List<Chapter> chapters = new ArrayList<>();
        chapters.add(new Chapter(1, "Reza", "Hello World", "..."));
        chapters.add(new Chapter(2, "Ali", "Hello Mars", "..."));
        chapters.add(new Chapter(3, "Mohsen", "Hello Mars", "..."));
        return chapters;
    }

    public List<Chapter> getFromDB() {
        List<Chapter> chapters = new ArrayList<>();
        DBHelper helper = new DBHelper(this.context);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from chapter", null);
        if (cursor.moveToFirst())
            do {
                Chapter chapter = new Chapter(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                chapters.add(chapter);
            } while (cursor.moveToNext());
            return chapters;
    }
}