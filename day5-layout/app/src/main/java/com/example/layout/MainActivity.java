package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FLRFragment fragment = new FLRFragment();
        LogFragment logfragment = new LogFragment();
        //UpMiFragment upMiFragment new UpMiFragment();
        DownMiFragment downMiFragment = new DownMiFragment();
        QuestionFragment questionFragment = new QuestionFragment();
        LogqueFragment logqueFragment = new LogqueFragment();
        ChangeinfFragment changeinfFragment =new ChangeinfFragment();
        SecFragment secFragment =new SecFragment();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.add(R.id.fragment_container,fragment);
        fragmentTransaction.add(R.id.fragment_container1,downMiFragment);
        fragmentTransaction.add(R.id.fragment_container2,questionFragment);
        fragmentTransaction.commit();


        //替换fragment
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.fragment_container, fragment);
        //fragmentTransaction.commit();

        Button button = findViewById(R.id.norque);
        Button button2 = findViewById(R.id.log);
        Button button3 = findViewById(R.id.changeinf);
        Button button4 = findViewById(R.id.securty);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container2, downMiFragment);
                fragmentTransaction.commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container2, logqueFragment);
                fragmentTransaction.commit();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container2, changeinfFragment);
                fragmentTransaction.commit();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container2, secFragment);
                fragmentTransaction.commit();
            }
        });

    }

}