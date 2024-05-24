package com.ganghuang.mlc2_android.TestModules;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

public class TestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.testForeceOffLine();
    }

    private  void testForeceOffLine(){//测试强制下线 遇到问题：无法接受广播，可能哪个地方配置错了，因为之前看的比较老的android书籍，大概还需要其他配置吧
        Button forceOffline = (Button) findViewById(R.id.test_main_activity_force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE OFFLINE");
                intent.setPackage("com.ganghuang.mlc2_android");
                sendBroadcast(intent);
            }
        });
    }
}


