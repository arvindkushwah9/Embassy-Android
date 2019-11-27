package com.example.amal.esa.ui.notification;

public class Service {

public Integer id;
public String title;
public String image;
public String description;
public String createdAt;
public Integer creatorId;

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", creatorId=" + creatorId +
                '}';
    }
}