package com.dushreza.festive.test;

import android.content.Context;

import com.dushreza.festive.test.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    public Repository() {
    }

    public static List<Chapter> getChapters() {
        List<Chapter> chapters = new ArrayList<>();
        chapters.add(new Chapter(1, "Reza", "Hello World, this is for the masses the lower classes.Java side note: It is never a good idea to \"stringify\" integer like that (especially for example purposes), and unfortunately it is frequently considered a good, quick way to convert int to string in java: ", "01.jpg"));
        chapters.add(new Chapter(2, "Ali", "Hello Mars.In the past, having splash screens in your Android app were not recommended. It didn’t make much sense to intentionally delay the user by adding a splash screen that shows for x seconds", "02.jpg"));
        chapters.add(new Chapter(3, "Mohsen", "Hello Mars.When your app is launched and it isn’t in memory yet, there may be some delay between when the user starts your app and when your launcher Activity’s onCreate() is actually called.", "03.jpg"));
        return chapters;
    }
}