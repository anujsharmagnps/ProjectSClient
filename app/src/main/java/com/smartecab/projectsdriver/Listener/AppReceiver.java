package com.smartecab.projectsdriver.Listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.smartecab.projectsdriver.Event.SettingChange;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by pc on 11/18/2017.
 */

public class AppReceiver extends BroadcastReceiver {
    @Inject
    EventBus eventBus;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            eventBus.post(new SettingChange("GPS"));
        }

    }
}