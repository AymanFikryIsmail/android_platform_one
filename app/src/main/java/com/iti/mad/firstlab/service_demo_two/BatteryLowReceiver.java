package com.iti.mad.firstlab.service_demo_two;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class BatteryLowReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

//        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//        sendIntent.putExtra("sms_body", "el baaaaaaaaaaatery ");
//        sendIntent.setType("vnd.android-dir/mms-sms");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(sendIntent);
        SmsManager sm = SmsManager.getDefault();
        sm.sendTextMessage("tel:121", null, "test message", null,
                null);
        Toast.makeText(context, "Action: " + intent.getAction(), Toast.LENGTH_LONG).show();

    }
}
