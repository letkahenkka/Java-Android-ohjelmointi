package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AirPlaneModeReceiver extends BroadcastReceiver {

    private final static String TAG = "AirPlaneModeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");
        boolean state = intent.getBooleanExtra("state", false);
        Log.e(TAG, "tuleeko onReceive-metodiin? state=" + state);
        int duration = Toast.LENGTH_SHORT;
        if(state == true){
            CharSequence text = "Airplane mode on";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            CharSequence text = "Airplane mode off";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}