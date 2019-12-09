package com.example.amal.esa.ui.admarket;

public class AdMarket {

public Integer id;
public String title;
public String image;
public String description;
public String createdAt;
public Integer creatorId;

    @Override
    public String toString() {
        return "AdMarket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", creatorId=" + creatorId +
                '}';
    }
}