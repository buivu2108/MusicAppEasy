package com.example.easytutomusicapp;

import java.io.Serializable;

public class AudioModel implements Serializable {
    int path;
    String title;
    String duration;

    public AudioModel(int path, String title, String duration) {
        this.path = path;
        this.title = title;
        this.duration = duration;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
