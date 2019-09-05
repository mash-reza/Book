package com.example.book.model;

public class Chapter {
    private int id;
    private String title;
    private String content;
    private String image;
    private int favorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public Chapter(int id, String title, String content, String image, int favorite) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.favorite = favorite;
    }
}
