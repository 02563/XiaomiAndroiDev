package com.example.permissions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpActivity extends AppCompatActivity {
    TextView responseText;
    Button button;
    //1.创建OkHttpClient
    //private final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    //2.创建Request
    //Request request =new Request.Builder()
    //        .get()
    //        .url("https://hotfix-service-prod.g.mi.com/quick-game/game/109")
    //        .build();


    //Request request =new Request.Builder()
    //        //.url("https://hotfix-service-prod.g.mi.com/quick-game/game/109")
    //        .url("https://www.baidu.com")
    //        .get()
    //        .post(new FormBody.Builder().build())
    //        .delete()
    //        .put(new FormBody.Builder().build())
    //        .head()
    //        .patch(new FormBody.Builder().build())
    //        .head()
    //        .patch(new FormBody.Builder().build())
    //        .addHeader( "key",  "value" )
    //        .build();
//
    ////3.同步请求
    //Response response;
//
    //{
    //    try {
    //        response = okHttpClient.newCall(request).execute();
    //    } catch (IOException e) {
    //        throw new RuntimeException(e);
    //    }
    //}
//
    //ResponseBody responseBody = response.body();

    private void showResponse(String responseBody) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            // 在这里进行UI操作，将结果显示到界面上
                responseText = findViewById(R.id.responseText);
                responseText.setText(responseBody);
            }
        });
    }


    //public static final MediaType FORM_CONTENT_TYPE= MediaType.parse("application/json;char
    //public void post(string phone){
    //    HashMap<String,String>hashMap=new HashMap<>();
    //    hashMap.put("phone",phone);
    //    String json=GsonUtil.getGson().toJson(hashMap);
    //    RequestBody requestBody= RequestBody.create(FORM_CONTENT TYPE, json);
    //    Request request = new Request.Builder().post(requestBody)
    //            .addHeader("content-type","application/json")
    //            .url("https://hotfix-service-prod,g.mi.com/quick-game/api/auth/sendcode")
    //            .build();
    //    okHttpClient.newCall(request).enqueue(new Callback(){
    //        @override
    //        public void onFailure(eNonNull Call call, @NonNull I0Exception e){
    //            LOg.(TAG，"网络请求失败");
    //            @0verride
    //            public void onResponse(@NonNull Call call, @NonNull Response response) throws Io
    //            LOg.(TAG，“网络请求成功，code"+response.code());
    //            if(response.isSuccessful()){
    //                ResponseBody body=response.body();
    //                if(body != null){
    //                    LOg.(TAG，"网络请求结果:"+body.string());
    //                });
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        button = findViewById(R.id.sendRequestBtn);
        button.setOnClickListener(v->{
            sendRequestWithOkHttp();
        });
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
                            .url("https://www.baidu.com")
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

}