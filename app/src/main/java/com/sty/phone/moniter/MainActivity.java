package com.sty.phone.moniter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sty.phone.moniter.receiver.ScreenReceiver;
import com.sty.phone.moniter.service.PhoneService;
import com.sty.phone.moniter.service.ScreenService;

public class MainActivity extends AppCompatActivity {
    private Button btnStartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ScreenService.class);
        //开启解锁锁屏处理的服务
        startService(intent);


        btnStartService = findViewById(R.id.btn_start_service);
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMonitorPhone();
            }
        });
    }

    /**
     * 将以下的逻辑放到开机启动的广播中执行，此按钮可以不点击
     */
    private void startMonitorPhone(){
        Intent intent = new Intent(this, PhoneService.class);
        startService(intent);
    }
}
