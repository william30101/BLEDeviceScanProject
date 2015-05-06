package com.example.android.bluetoothlegatt;

import java.util.Date;

import com.example.android.bluetoothlegatt.MyAIDLService.Stub;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

	public static final String TAG = "william";
	//private MyBinder mBinder = new MyBinder();  
	private MyAIDLService.Stub mBinder; 
	
	private Handler handler = new Handler();
	  
	 private Runnable showTime = new Runnable() {
	  public void run() {
	   // log目前時間
	   Log.i(TAG, new Date().toString());
	   handler.postDelayed(this, 1000);
	  }
	 };
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate() {
		//super.onCreate();
		//Log.d(TAG, "onCreate() executed");
		
		
		 super.onCreate();  
	        Notification notification = new Notification(R.drawable.ic_launcher,  
	                "notify coming", System.currentTimeMillis());  
	        Intent notificationIntent = new Intent(this, DeviceControlActivity.class);  
	        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,  
	                notificationIntent, 0);  
	        notification.setLatestEventInfo(this, "Title here", "Context here",  
	                pendingIntent);  
	        startForeground(1, notification);  
	        Log.d(TAG, "onCreate() executed"); 

	        mBinder = new Stub() {  
	  		  
	            @Override  
	            public String toUpperCase(String str) throws RemoteException {  
	                if (str != null) {  
	                    return str.toUpperCase();  
	                }  
	                return null;  
	            }

				@Override
				public int plus(int a, int b) throws RemoteException {
					// TODO Auto-generated method stub
					return a + b;  
				}  
	        };
	      
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand() executed");
		handler.postDelayed(showTime, 1000);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() executed");
		handler.removeCallbacks(showTime);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	
	class MyBinder extends Binder {  
		  
        public void startDownload() {  
            Log.d(TAG, "startDownload() executed");  
            // 执行具体的下载任务  
        }  
  
    }  

}