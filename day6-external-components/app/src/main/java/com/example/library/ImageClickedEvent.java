package com.example.library;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.library.Adapter.item;

public class ImageClickedEvent {
    private item imageId;

    public ImageClickedEvent(item drawable) {
        this.imageId = drawable;
    }

    public item getImage() {
        return imageId;
    }
}
