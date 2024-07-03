package com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> t= new ArrayList<>();
        t.add("标签1");
        t.add("标签2fdafdsa");
        t.add("标签3");
        t.add("标签4");
        t.add("标签5");

        ((TagCloud)findViewById(R.id.edit)).setTag(t);


    }
}