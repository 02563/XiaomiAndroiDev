package com.example.library;

import com.example.library.Adapter.item;

public class TextClickEvent {
    private item text;

    public TextClickEvent(item text) {
        this.text = text;
    }

    public item getText() {
        return text;
    }
}
