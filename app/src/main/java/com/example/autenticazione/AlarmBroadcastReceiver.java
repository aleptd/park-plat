package com.example.autenticazione;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_ALARM = "com.example.alarms.ATCION_ALARM";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_ALARM.equals(intent.getAction())) {
            Log.i("mylog", "ecco l'alarm");
            Toast.makeText(context, ACTION_ALARM, Toast.LENGTH_SHORT).show();

        }
    }
}