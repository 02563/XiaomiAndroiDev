package com.example.fourcomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_logging);

        Button button = findViewById(R.id.ServiceButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐式intent
                Intent sendIntent = new Intent(ServiceActivity.this,FirstService.class);
                startActivity(sendIntent);
            }
        });
    }

}