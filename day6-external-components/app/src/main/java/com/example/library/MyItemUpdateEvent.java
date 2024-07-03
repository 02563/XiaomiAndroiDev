package com.example.library;

import com.example.library.Adapter.item;

public class MyItemUpdateEvent {
    private item updatedItem;

    public MyItemUpdateEvent(item updatedItem) {
        this.updatedItem = updatedItem;
    }

    public item getUpdatedItem() {
        return updatedItem;
    }
}
