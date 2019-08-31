package com.dushreza.festive.test;

import java.util.ArrayList;
import java.util.List;

public class DataFakeGenerator {
    public static List<Chapter> getChapters(){
        List<Chapter> chapters = new ArrayList<>();
        chapters.add(new Chapter(1,"Reza", "Hello World", "..."));
        chapters.add(new Chapter(2,"Ali", "Hello Mars", "..."));
        chapters.add(new Chapter(3,"Mohsen", "Hello Mars", "..."));
        return chapters;
    }
}