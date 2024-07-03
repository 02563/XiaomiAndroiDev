package com.example.permissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.anrwatchdog.ANRWatchDog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {
    private List<Game> gameList;
    private GameAdapter gameAdapter;
    private RecyclerView gameRecyclerView;
    private int i;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isRefreshing = false;
    private boolean isLoadingMore = false;
    private int currentPage = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ANRWatchDog(10000).start(); // 设置超时时间为10秒

        setContentView(R.layout.activity_search);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        gameRecyclerView = findViewById(R.id.rv_search_results);

        EditText editText = findViewById(R.id.et_search); // 获取EditText实例

        Button button = findViewById(R.id.btn_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editText.getText().toString(); // 获取输入的文本并转换为字符串
                gameList.clear();
                try {
                    currentPage = Integer.parseInt(inputText);
                } catch (NumberFormatException e) {
                    System.out.println("无法将字符串转换为整数");
                }
                if(inputText != null){
                    getGameInfo(currentPage);
                }else {
                    gameList = null;
                    for(int i=0;i<6;i++){
                        getGameInfo(i);
                    }
                    gameAdapter.notifyDataSetChanged();
                }

            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isRefreshing) {
                    isRefreshing = true;
                    currentPage = 1;
                    gameList.clear();
                    getGameInfo(currentPage);
                }
            }
        });

        gameRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 检查是否滚动到了底部
                if (!isLoadingMore && !recyclerView.canScrollVertically(1)) {
                    isLoadingMore = true;
                    currentPage++;
                    getGameInfo(currentPage);
                }
            }
        });

        gameList = new ArrayList<>();
        gameRecyclerView = findViewById(R.id.rv_search_results);
        gameAdapter = new GameAdapter(this, gameList);
        gameRecyclerView.setAdapter(gameAdapter);
        gameRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //gameList.add(new Game("Game Name", R.drawable.game_image, 4.5));
        gameAdapter.notifyDataSetChanged();
        getGameInfo(1);
        getGameInfo(2);
        getGameInfo(3);
        getGameInfo(4);
        getGameInfo(5);
        getGameInfo(6);
    }
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            // .url("http://10.0.2.2/get_data.xml")
                            .url("https://hotfix-service-prod.g.mi.com/quick-game/game/1")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    if (responseData != null) {
                        // parseXMLWithPull(responseData);
                        // parseXMLWithSAX(responseData);
                        // parseJSONWithJSONObject(responseData);
                        //parseJSONWithGSON(responseData);
                        showResponse(responseData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showResponse(String responseBody) {
       // runOnUiThread(new Runnable() {
       //     @Override
       //     public void run() {
       //         // 在这里进行UI操作，将结果显示到界面上
       //         responseText = findViewById(R.id.responseText);
       //         responseText.setText(responseBody);
       //     }
       // });
        try {
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONObject dataObject = jsonObject.getJSONObject("data");

            String gameName = dataObject.getString("gameName");
            String iconUrl = dataObject.getString("icon");
            double score = dataObject.getDouble("score");

            // 创建一个新的Game对象，并将提取的信息设置给它
            Game game = new Game(gameName, iconUrl, score);

            // 在主线程中更新UI
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 将Game对象添加到gameList中
                    gameList.add(game);

                    // 通知适配器数据已更改
                    gameAdapter.notifyDataSetChanged();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getGameInfo(int id) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://hotfix-service-prod.g.mi.com/quick-game/game/" + id;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    showResponse(responseBody);
                }
                // 刷新完成后将isRefreshing设置为false，并停止刷新状态
                isRefreshing = false;
                isLoadingMore = false;
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        currentPage++;
    }

}