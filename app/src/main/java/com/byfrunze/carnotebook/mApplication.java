package com.byfrunze.carnotebook;

import android.app.Application;

import io.realm.Realm;

public class mApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
