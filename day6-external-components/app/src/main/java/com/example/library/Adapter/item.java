package com.example.library.Adapter;

public class item {
    private int itemId;
    private String ItemName;
    private boolean isLiked;

     //构造函数
    public item(String ItemNamerec,int itemIdrec) {
        this.itemId = itemIdrec;
        this.ItemName = ItemNamerec;
        this.isLiked = false;
    }

    //public item() {
    //}
    // Getters and setters
    public int getitemId() {
        return itemId;
    }

    public void setitemId(int itemIdrec) {
        this.itemId = itemIdrec;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemNamerec) {
        this.ItemName = ItemNamerec;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

}
