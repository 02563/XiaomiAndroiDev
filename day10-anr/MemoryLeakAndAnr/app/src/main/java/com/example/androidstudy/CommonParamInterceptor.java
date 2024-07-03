package com.example.androidstudy;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

public class CommonParamInterceptor implements Interceptor {
    private final Context context;

    public CommonParamInterceptor(Context context) {
        this.context = context;
    }

    private int getAppVersionCode() {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        HttpUrl.Builder builder = chain.request().url().newBuilder();
        builder.addQueryParameter("appVersionCode", Integer.toString(getAppVersionCode()));
        return chain.proceed(chain.request());
    }
}
