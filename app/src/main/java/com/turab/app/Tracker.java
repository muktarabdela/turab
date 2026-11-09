package com.turab.app;

import android.content.Context;
import android.content.SharedPreferences;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class Tracker {
    private static final String PREF = "turab_private";
    private static final String START = "start_date";
    private static final String RECORD = "longest";
    private static final String RESET = "last_reset";
    private Tracker() {}
    static SharedPreferences prefs(Context c) {
        return c.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }
    static LocalDate start(Context c) {
        String s = prefs(c).getString(START, "");
        if (s.isEmpty()) return null;
        try { return LocalDate.parse(s); } catch (Exception e) { return null; }
    }
    static int streak(Context c) {
        LocalDate s = start(c);
        if (s == null) return 0;
        long days = ChronoUnit.DAYS.between(s, LocalDate.now()) + 1;
        return (int)Math.max(0, days);
    }
    static int record(Context c) { return prefs(c).getInt(RECORD, 0); }
    static void begin(Context c) {
        if (start(c) == null) {
            prefs(c).edit().putString(START, LocalDate.now().toString()).apply();
            refresh(c);
        }
    }
    static void reset(Context c) {
        int current = streak(c);
        int best = Math.max(record(c), current);
        prefs(c).edit().putInt(RECORD, best)
            .putString(START, "")
            .putString(RESET, LocalDate.now().toString()).apply();
        refresh(c);
    }
    static void refresh(Context c) {
        int current = streak(c);
        if (current > record(c)) prefs(c).edit().putInt(RECORD, current).apply();
        TurabWidgetProvider.updateAll(c);
    }
}
