package com.ganghuang.mlc2_android.TestModules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TestMyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "😯自己自定义的广播 received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}

