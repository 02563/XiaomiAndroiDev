package com.example.androidstudy;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends Activity {
    private static final String TAG = "PermissionActivity";
    private static final List<Activity> leaks = new ArrayList<>();
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leaks.add(this);
        setContentView(R.layout.activity_permisson);
        findViewById(R.id.startCamera).setOnClickListener(view -> {
            startCameraWithCheckPermission();
        });
        findViewById(R.id.scanSd).setOnClickListener(v -> scanSdWithCheckPermission());
        findViewById(R.id.call).setOnClickListener(v -> callWithCheckPermission());
        linearLayout = findViewById(R.id.container);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        leaks.remove(this); // 从leaks中移除当前Activity对象，避免内存泄露
    }

    private void callWithCheckPermission() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int flag = checkSelfPermission(Manifest.permission.CALL_PHONE);
        if (flag == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "有拨打电话权限", Toast.LENGTH_SHORT).show();
            call();
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 400);
        }
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:5566664444"));
        startActivity(intent);
    }


    private void scanSd() {
        File file = Environment.getExternalStorageDirectory();

        showFiles(file);
    }

    private void showFiles(File file) {
        if (file.isFile()) return;
        File[] files = file.listFiles();
        if (files == null) return;
        for (int i = 0; i < files.length; i++) {
            TextView textView = new TextView(this);
            File file1 = files[i];
            boolean directory = file1.isDirectory();
            String type = directory ? "文件夹" : "文件";
            int subLength = 0;
            textView.setText(file1.getName() + ", 文件类型：" + type);
            textView.setTag(file1);
            linearLayout.addView(textView);
        }
        // 关闭文件流
        // file.close();
    }

    private void scanSdWithCheckPermission() {
        int flag = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (flag == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "有sd卡写权限", Toast.LENGTH_SHORT).show();
            scanSd();
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 300);
        }
    }

    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivity(intent);
    }

    private void startCameraWithCheckPermission() {
        int flag = checkSelfPermission(Manifest.permission.CAMERA);
        if (flag == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "已获得相机权限", Toast.LENGTH_SHORT).show();
            startCamera();
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "相机权限被授予", Toast.LENGTH_SHORT).show();
                startCamera();
            } else {
                Toast.makeText(this, "相机权限被拒绝", Toast.LENGTH_SHORT).show();
                jumpSetting();
            }
        } else if (requestCode == 300) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "sd卡被授予", Toast.LENGTH_SHORT).show();
                scanSd();
            } else {
                Toast.makeText(this, "sd卡权限被拒绝", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 400) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "拨打电话被授予", Toast.LENGTH_SHORT).show();
                call();
            } else {
                Toast.makeText(this, "拨打电话被拒绝", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void jumpSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}