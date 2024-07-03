package com.example.androidui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    // 首先，确保 data 是一个已经初始化的列表
    List<GameBean> data = new ArrayList<>();
    GameAdapter adapter;
    int number;

    private int gameIdCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button addbutton = findViewById(R.id.add);
        Button deletebutton = findViewById(R.id.delete);
        RecyclerView recyclerView = findViewById(R.id.demo_recyclerview);
        setDate();
        adapter = new GameAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addbutton.setOnClickListener(view -> addGame());
        deletebutton.setOnClickListener(view -> removeGame());
        //    @Override
        //    public void onClick(View v) {
        //        GameBean newgame = new GameBean();
        //        newgame.setGameIcon(R.drawable.celeste);
        //        newgame.setGameName("newgame");
        //        newgame.setGameStatus("downlown");
        //        data.add(newgame);
        //        GameAdapter adapter = new GameAdapter(data);
        //        adapter.notifyItemInserted(data.size() - 1);
        //    }
        //});

    }


    private void addGame() {
        EditText editText = findViewById(R.id.position); // 替换为你的EditText的ID
        String text = editText.getText().toString(); // 将Editable对象转换为String类型
        try {
            number = Integer.parseInt(text); // 将字符串转换为整数
            // 现在你可以使用这个整数变量number了
        } catch (NumberFormatException e) {
            // 如果字符串不能转换为整数，会抛出NumberFormatException异常
            // 在这里处理错误，比如显示一个Toast提示输入正确的数字
            Toast.makeText(this, "请输入正确的数字", Toast.LENGTH_SHORT).show();
        }
        // 获取基于0的索引位置
        int positionToInsert = number -1 ;


        // 创建新的 GameBean 对象
        GameBean newGame = new GameBean();
        gameIdCounter++;
        newGame.setGameIcon(R.drawable.celeste);
        newGame.setGameName(number);
        newGame.setGameStatus("Download");
        // If the number is less than 1, insert at the beginning of the list
        if (positionToInsert < 0) {
            positionToInsert = 0;
            newGame.setGameName(1);
        }


        // Insert the new game at the specified position
        data.add(positionToInsert, newGame);

        // Notify the adapter about the item insertion
        adapter.notifyItemInserted(positionToInsert);

        // Update the names of the items affected by the insertion
        for (int i = positionToInsert + 1; i < data.size(); i++) {
            data.get(i).setGameName((i + 1));
        }

        // If necessary, notify the adapter to update the range of items
        if (data.size() > positionToInsert + 1) {
            adapter.notifyItemRangeChanged(positionToInsert + 1, data.size() - positionToInsert - 1);
        }
    }

    private void removeGame() {
        EditText editText = findViewById(R.id.position); // 替换为你的EditText的ID
        String text = editText.getText().toString(); // 将Editable对象转换为String类型
        try {
            number = Integer.parseInt(text); // 将字符串转换为整数
            // 现在你可以使用这个整数变量number了
        } catch (NumberFormatException e) {
            // 如果字符串不能转换为整数，会抛出NumberFormatException异常
            // 在这里处理错误，比如显示一个Toast提示输入正确的数字
            Toast.makeText(this, "请输入正确的数字", Toast.LENGTH_SHORT).show();
        }
        // 获取基于0的索引位置
        int positionToRemove = number - 1;

        // 检查位置是否有效
        if (positionToRemove >= 0 && positionToRemove < data.size()) {
            // 移除指定位置的游戏
            data.remove(positionToRemove);
            // 通知适配器项目已移除
            adapter.notifyItemRemoved(positionToRemove);
            // 更新后续项目的编号
            for (int i = positionToRemove; i < data.size(); i++) {
                data.get(i).setGameName((i + 1));
            }
            // 如果需要，更新受影响的项
            if (data.size() > positionToRemove) {
                adapter.notifyItemRangeChanged(positionToRemove, data.size() - positionToRemove);
            }
        } else {
            // 如果位置无效，显示提示
            Toast.makeText(this, "Invalid position for removing game", Toast.LENGTH_SHORT).show();
        }
    }

        private void setDate(){
            String[] gameStatus = new String[]{"start","wait","update","book"};
            int[] icons = new int[]{R.drawable.celeste,R.drawable.celeste,R.drawable.celeste,R.drawable.celeste};
            for(int i=0; i<50; i++){
                GameBean game =new GameBean();
                game.setGameName(i+1);
                game.setGameStatus(gameStatus[i%4]);
                game.setGameIcon(icons[i%4]);
                data.add(game);
            }
        }
    public void gameclick(View view) {
        String buttonText = ((Button) view).getText().toString();
        Toast.makeText(MainActivity3.this, buttonText, Toast.LENGTH_SHORT).show();

    }


}
