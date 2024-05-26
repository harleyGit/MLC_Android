package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

public class TestAndroidThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;

    public  static void actionJumpToTestAndroidThreadActivity(Context context){
        Intent intent = new Intent(context, TestAndroidThreadActivity.class);

        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_android_thread);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.testThread00();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.test_android_thread_btn_change_text_) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    text.setText("很高兴见到你\n Nice to meet you");
                }
            }).start();
        }
    }


    private void testThread00() {//多线程处理方法
        text = findViewById(R.id.test_android_thread_text);
        Button btn = findViewById(R.id.test_android_thread_btn_change_text_);

        btn.setOnClickListener(this);
    }


}