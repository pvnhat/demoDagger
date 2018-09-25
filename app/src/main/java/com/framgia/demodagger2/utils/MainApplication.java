package com.framgia.demodagger2.utils;

import android.app.Application;
import com.framgia.demodagger2.data.source.remote.NetworkModule;

public class MainApplication extends Application {

    private AppComponent mAppComponent;

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .applicationModule(new ApplicationModule(getApplicationContext()))
                    .networkModule(new NetworkModule(this))
                    .build();
        }
        return mAppComponent;
    }
}
