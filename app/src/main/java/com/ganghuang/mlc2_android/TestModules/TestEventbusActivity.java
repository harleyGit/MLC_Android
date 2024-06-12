package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TestEventbusActivity extends AppCompatActivity implements View.OnClickListener {
    public static void actionJumpToTestEventbusActivity(Context context) {//跳转到TestEventbusActivity
        Intent intent = new Intent(context, TestEventbusActivity.class);

        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_eventbus);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.testEventbus00();
    }


    private void testEventbus00(){
        Button btn00 = findViewById(R.id.activity_test_eventbus_btn00);
        btn00.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_test_eventbus_btn00){
            // 发布事件
            // 启动 SecondActivity
            startActivity(new Intent(TestEventbusActivity.this, TestEventbusSecondActivity.class));


            // 添加延迟发送事件
            int delayMillis = 1000; // 1秒
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new MessageEvent("Hello 因为我要在注册前才能接收到消息，不好意思，来迟了!‼️"));
                }
            }, delayMillis);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
