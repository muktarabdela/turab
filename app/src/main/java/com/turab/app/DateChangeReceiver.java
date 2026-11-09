package com.turab.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DateChangeReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
        Tracker.refresh(context);
    }
}
