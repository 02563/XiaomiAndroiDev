package com.example.library;

public class LoveEvent {
    private int itemId;
    private boolean isLiked;

    public LoveEvent(int itemId, boolean isLiked) {
        this.itemId = itemId;
        this.isLiked = isLiked;
    }

    public int getItemId() {
        return itemId;
    }

    public boolean isLiked() {
        return isLiked;
    }
}
