package com.example.androidstudy;

import android.content.Context;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ServerApiManager {
    private final OkHttpClient okHttpClient;
    private final Gson gson = new Gson();
    private final ApiService apiService;

    interface ApiService {
        @GET("/quick-game/game/{id}")
        retrofit2.Call<CommonData<GameItem>> queryGame(@Path("id") String id);
    }

    public ServerApiManager(Context context) {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new SlowNetworkInterceptor())
                .addInterceptor(new CommonParamInterceptor(context))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hotfix-service-prod.g.mi.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static synchronized ServerApiManager getInstance(Context context) {
        return new ServerApiManager(context);
    }

    public ApiService getApiService() {
        return apiService;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Gson getGson() {
        return gson;
    }
}
