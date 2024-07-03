package com.example.permissions;

public class Game {
    private String  GameName;
    private String GameImg;
    private double Score;
    public Game(String ItemName,String itemImg,double score) {
        this.GameName = ItemName;
        this.GameImg = itemImg;
        this.Score = score;
    }

    //public item() {
    //}
    // Getters and setters
    public String getGameName() {
        return GameName;
    }

    public void setGameName(String itemIdrec) {
        this.GameName = itemIdrec;
    }

    public String getGameImg() {
        return GameImg;
    }

    public void setGameImg(String ItemNamerec) {
        this.GameImg = ItemNamerec;
    }

    public double getScore() {
        return Score;
    }


    public void setScore(double liked) {
        Score = liked;
    }
}
