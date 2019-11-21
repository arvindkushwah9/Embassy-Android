package com.example.amal.esa.ui.notification;

import com.example.amal.esa.ui.tracking.Document;

import java.util.ArrayList;

public class Notification {
    public Integer id;
    public String title;

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}