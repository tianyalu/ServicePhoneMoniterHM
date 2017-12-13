package com.sty.phone.moniter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *
 *
 * Created by Steven.T on 2017/12/13/0013.
 */

public class ScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String content = intent.getAction();
        if("android.intent.action.SCREEN_OFF".equals(content)){
            Log.i("Tag", "屏幕熄灭了");
        }else if("android.intent.action.SCREEN_ON".equals(content)){
            Log.i("Tag", "屏幕被点亮了");
        }
    }
}
