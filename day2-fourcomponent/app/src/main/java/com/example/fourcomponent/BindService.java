package com.example.fourcomponent;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindService extends Service {
    private static final String TAG = "BindService";
    private final LocalBinder mBinder = new LocalBinder();
    public BindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG,"onBind()");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        Log.d(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    public String getBindText(){return "Hello world";}

    public class LocalBinder extends Binder {
        BindService getService(){return BindService.this;}
    }
}