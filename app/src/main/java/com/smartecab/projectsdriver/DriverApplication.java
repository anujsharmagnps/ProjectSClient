package com.smartecab.projectsdriver;

import android.app.Application;
import android.content.Context;

import com.smartecab.projectsdriver.DI.Component.AppComponent;
import com.smartecab.projectsdriver.DI.Component.DaggerAppComponent;
import com.smartecab.projectsdriver.DI.Module.AppModule;
import android.support.multidex.MultiDex;
/**
 * Created by rahul.sharma01 on 11/5/2017.
 */

public class DriverApplication extends Application {

    private static AppComponent appComponent;
    private static boolean activityVisible;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
