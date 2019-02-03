package com.iti.mad.firstlab.service_cast_demo_one;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DownloadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        if(intent.getAction().equals("com.android.downloadimage")) {

            Intent intent1 = new Intent(context, ResultActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra("imagePath", intent.getStringExtra("imagePath"));
            context.startActivity(intent1);
        }
    }
}
