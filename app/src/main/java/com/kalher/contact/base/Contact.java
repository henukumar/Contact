package com.kalher.contact.base;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Contact extends Application {

    // Avoid accessing sCurrentBaseActivity from static context
    private static BaseActivity sCurrentBaseActivity;
    private static Context sApplicationContext;

    private static ExecutorService sExecutor;
    private static final int THREAD_COUNT = 10;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = this;
        Stetho.initializeWithDefaults(this);
    }

    public static BaseActivity getsCurrentBaseActivity() {
        return sCurrentBaseActivity;
    }

    public static void setsCurrentBaseActivity(BaseActivity sCurrentBaseActivity) {
        Contact.sCurrentBaseActivity = sCurrentBaseActivity;
    }

    public static Context getsApplicationContext() {
        return sApplicationContext;
    }

    public static ExecutorService getExecutor(){
        if(sExecutor == null){
            sExecutor = Executors.newFixedThreadPool(THREAD_COUNT);
        }
        return sExecutor;
    }

}
