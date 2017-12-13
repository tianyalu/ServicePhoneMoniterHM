package com.sty.phone.moniter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sty.phone.moniter.service.PhoneService;

/**
 * Created by Steven.T on 2017/12/13/0013.
 */

public class BootReceiver extends BroadcastReceiver {
    //当手机重启时执行此方法
    @Override
    public void onReceive(Context context, Intent intent) {
        //把开启服务的逻辑放到这个方法中
        Intent intent2 = new Intent(context, PhoneService.class);
        //开启服务
        context.startService(intent2);
    }
}
