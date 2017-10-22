package com.grument.plinktestproject;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class PlinkTestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("plink.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}

