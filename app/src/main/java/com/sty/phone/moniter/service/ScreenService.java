package com.sty.phone.moniter.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.sty.phone.moniter.receiver.ScreenReceiver;

/**
 * Created by Steven.T on 2017/12/13/0013.
 */

public class ScreenService extends Service {
    private ScreenReceiver screenReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //当服务第一次启动的时候调用
    @Override
    public void onCreate() {
        //在这个方法中注册广播接收者
        //1.获取ScreenReceiver的实例
        screenReceiver = new ScreenReceiver();
        //2.创建IntentFilter的对象
        IntentFilter filter = new IntentFilter();
        //3.添加注册的事件
        filter.addAction("android.intent.action.SCREEN_OFF");
        filter.addAction("android.intent.action.SCREEN_ON");
        //4.通过代码的方式注册
        registerReceiver(screenReceiver, filter);

        super.onCreate();
    }

    //当服务销毁的时候调用
    @Override
    public void onDestroy() {
        unregisterReceiver(screenReceiver);
        super.onDestroy();
    }
}
