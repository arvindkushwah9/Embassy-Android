package com.example.amal.esa.ui.news;

public class Post {

public String title;
public String description;
public String content;
public String image;
public String pubDate;
public String updateDate;

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}