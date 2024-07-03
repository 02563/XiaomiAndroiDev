package com.example.fourcomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CalculatorServiceActivity extends AppCompatActivity {
    private static final String TAG = "CalculatorActivity";
    private IMyAidlInterface mBinderService;
    private boolean mBound = false;
    private ServiceConnection mConnection = new ServiceConnection(){
        @Override
        public void  onServiceConnected(ComponentName name, IBinder service){
            mBinderService = IMyAidlInterface.Stub.asInterface(service);
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {mBound = false; }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_logging);

        Intent intent = new Intent(this,MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Button calculateBtn = findViewById(R.id.btn_calculate);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound){
                    return;
                }
                try{
                    int sum = mBinderService.add(1,2);
                    Toast.makeText(CalculatorServiceActivity.this,"result="+sum,Toast.LENGTH_SHORT).show();
                }catch (RemoteException e){
                    Log.d(TAG,"error",e);
                }
            }
        });
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbindService(mConnection);
        mBound = false;
    }
}
