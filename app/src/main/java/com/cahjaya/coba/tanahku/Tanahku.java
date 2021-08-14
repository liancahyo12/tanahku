package com.cahjaya.coba.tanahku;

import android.app.Application;
import android.content.Context;

import com.cahjaya.coba.tanahku.network.ApiClient;
import com.cahjaya.coba.tanahku.network.ApiInterface;

public class Tanahku extends Application{

    private static Tanahku instance;

    public static Tanahku getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }


    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

}
