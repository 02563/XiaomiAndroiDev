package com.example.androidstudy;

public class GameItem {
    public String gameName;
    public String packageName;

    @Override
    public String toString() {
        return "GameItem{" +
                "gameName='" + gameName + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
