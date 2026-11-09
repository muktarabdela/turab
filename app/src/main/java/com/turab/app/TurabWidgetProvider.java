package com.turab.app;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

public class TurabWidgetProvider extends AppWidgetProvider {
    @Override public void onUpdate(Context context, AppWidgetManager manager, int[] ids) {
        for (int id : ids) update(context, manager, id);
    }
    static void updateAll(Context c) {
        AppWidgetManager m = AppWidgetManager.getInstance(c);
        int[] ids = m.getAppWidgetIds(new ComponentName(c, TurabWidgetProvider.class));
        for (int id : ids) update(c, m, id);
    }
    private static void update(Context c, AppWidgetManager m, int id) {
        int days = Tracker.streak(c);
        RemoteViews v = new RemoteViews(c.getPackageName(), R.layout.turab_widget);
        v.setTextViewText(R.id.widget_streak, days == 0 ? "START YOUR JOURNEY" : days + (days == 1 ? " DAY" : " DAYS"));
        String[] msgs = {"One day at a time.", "Stay focused. You've got this.", "Discipline is your strength.", "Look how far we've come!"};
        v.setTextViewText(R.id.widget_message, msgs[Math.min(3, days / 7)]);
        android.content.Intent launch = c.getPackageManager().getLaunchIntentForPackage(c.getPackageName());
        if (launch != null) {
            android.app.PendingIntent pi = android.app.PendingIntent.getActivity(c, 0, launch,
                android.app.PendingIntent.FLAG_UPDATE_CURRENT | android.app.PendingIntent.FLAG_IMMUTABLE);
            v.setOnClickPendingIntent(R.id.widget_root, pi);
        }
        m.updateAppWidget(id, v);
    }
}
