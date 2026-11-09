package com.turab.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView streak, message, record;
    private final String[] messages = {
        "Every journey starts somewhere.",
        "Stay focused. You've got this.",
        "One day at a time. Keep going.",
        "Discipline grows through small choices.",
        "Look how far you've come."
    };
    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        streak = findViewById(R.id.streak);
        message = findViewById(R.id.message);
        record = findViewById(R.id.record);
        Button start = findViewById(R.id.start);
        Button reset = findViewById(R.id.reset);
        start.setOnClickListener(v -> {
            Tracker.begin(this);
            render();
        });
        reset.setOnClickListener(v -> new AlertDialog.Builder(this)
            .setTitle("Restart your journey?")
            .setMessage("Your current streak will reset to zero. Your longest streak and achievements will be preserved.")
            .setNegativeButton("Cancel", (d,w) -> d.dismiss())
            .setPositiveButton("Confirm reset", (d,w) -> {
                Tracker.reset(this);
                render();
            }).show());
        render();
    }
    @Override protected void onResume() { super.onResume(); Tracker.refresh(this); render(); }
    private void render() {
        int days = Tracker.streak(this);
        streak.setText(days == 0 ? "Your journey hasn't started" : days + (days == 1 ? " DAY" : " DAYS"));
        message.setText(messages[Math.min(messages.length - 1, Math.max(0, days / 7))]);
        record.setText("Longest streak: " + Tracker.record(this) + " days");
        Button start = findViewById(R.id.start);
        start.setEnabled(days == 0);
        start.setText(days == 0 ? "Start Journey" : "Journey in progress");
    }
}
