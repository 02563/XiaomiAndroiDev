package com.example.androidui;

public class GameBean {
    private int getGameName;
    private String getGameStatus;
    private int getGameIcon;

    // 构造函数
    //public GameBean(String title, String description, int imageResourceId) {
    //    this.getGameName = title;
    //    this.getGameStatus = description;
    //    this.getGameIcon = imageResourceId;
    //}
    public GameBean() {
    }

    // Getters and setters
    public int getGameName() {
        return getGameName;
    }

    public void setGameName(int title) {
        this.getGameName = title;
    }

    public String getGameStatus() {
        return getGameStatus;
    }

    public void setGameStatus(String description) {
        this.getGameStatus = description;
    }

    public int getGameIcon() {
        return getGameIcon;
    }

    public void setGameIcon(int imageResourceId) {
        this.getGameIcon = imageResourceId;
    }
}
