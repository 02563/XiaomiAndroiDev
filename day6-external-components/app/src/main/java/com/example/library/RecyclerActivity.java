package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.Adapter.ColorAdapter;
import com.example.library.Adapter.item;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView imageViewlike;
    private List<item> mList = new ArrayList<>();
    private item myitem=new item("Item 3", R.drawable.celeste);
    Boolean love =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        // 获取ImageView
        imageView = findViewById(R.id.detile_image);
        imageViewlike = findViewById(R.id.likeImageView2);

        mList.add(myitem);
// 获取传递过来的数据模型
        //myitem = getIntent().getParcelableExtra("item");

        // 订阅EventBus事件
        EventBus.getDefault().register(this);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建并替换新的fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RecyclerFragment newFragment = new RecyclerFragment();
                fragmentTransaction.replace(R.id.fragment_container, newFragment);
                fragmentTransaction.commit();
            }
        });


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建并替换新的fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MineFragment newFragment = new MineFragment();
                fragmentTransaction.replace(R.id.fragment_container, newFragment);
                fragmentTransaction.commit();
            }
        });

        RecyclerFragment recyclerFragment =new RecyclerFragment();
        // 获取 FragmentManager 并开始事务
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 将 Fragment 添加到容器中
        fragmentTransaction.replace(R.id.fragment_container, recyclerFragment);

        // 提交事务
        fragmentTransaction.commit();

        // 设置喜欢图标的状态
        ImageView likeImageView = findViewById(R.id.likeImageView2);
        if (myitem.isLiked()) {
            likeImageView.setImageResource(R.drawable.like);
        } else {
            likeImageView.setImageResource(R.drawable.nolike);
        }



    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTextClickEvent(TextClickEvent event) {
        item text = event.getText();

        // 更新TextView的文本
        TextView textView = findViewById(R.id.detile_text);
        textView.setText(text.getItemName());
        // 获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 获取要移除的Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container); // R.id.fragment_container是您在Activity布局文件中用于容纳Fragment的容器的ID
        // 开始Fragment事务并移除Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onImageClickEvent(ImageClickedEvent event) {
        item imageView1 = event.getImage();
        myitem = imageView1;
        if(imageView1.isLiked()){
            imageViewlike.setImageResource(R.drawable.like);
        }

        imageView.setImageResource(imageView1.getitemId());
        // 获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 获取要移除的Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container); // R.id.fragment_container是您在Activity布局文件中用于容纳Fragment的容器的ID
        // 开始Fragment事务并移除Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 取消订阅EventBus事件
        EventBus.getDefault().unregister(this);
    }

    public void Changelike(View view) {
        // 处理ImageText的点击事件
        if (myitem.isLiked()) {
            imageViewlike.setImageResource(R.drawable.nolike);
            myitem.setLiked(false);
        } else {
            imageViewlike.setImageResource(R.drawable.like);
            myitem.setLiked(true);
        }
        // 发布事件并传递更新后的myitem
        EventBus.getDefault().post(new MyItemUpdateEvent(myitem));
    }
    public List<item> getMList() {
        return mList;
    }

}