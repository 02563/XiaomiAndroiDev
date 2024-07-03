package com.example.androidui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.GameManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SeekBar seekBar = findViewById(R.id.set_rotate);
        ImageView imageView = findViewById(R.id.image);
        TextView rotateText = findViewById(R.id.rotate_text);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageView.setPivotX(imageView.getWidth()/2);
                imageView.setPivotY(imageView.getHeight()/2);
                float alpha = progress/360.0f;
                imageView.setRotation(progress);
                imageView.setAlpha(alpha);
                rotateText.setText("设置旋转角度: "+progress+"设置透明度: "+alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void goToNextActivity2(View view) {
        // 创建Intent来启动NextActivity
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        // 启动NextActivity
        startActivity(intent);
    }
}