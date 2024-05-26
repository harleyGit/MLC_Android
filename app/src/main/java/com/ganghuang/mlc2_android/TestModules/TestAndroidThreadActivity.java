package com.ganghuang.mlc2_android.TestModules;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

    //定义了一个整型常量UPDATE_TEXT，用于表示更新TextView这个动作。
    public static final int UPDATE_TEXT = 1;


    /**
     * 新增一个Handler对象，并重写父类的handleMessage()方法，在这里对具体的Message进行处理。
     * 如果发现Message的what字段的值等于UPDATE_TEXT，就将TextView显示的内容改成Nice to meet you。
     *
     *
     * Handler顾名思义也就是处理者的意思，它主要是用于发送和处理消息的。
     * 发送消息一般是使用Handler的sendMessage()方法，而发出的消息经过一系列地辗转处理后，最终会传递到Handler的handleMessage()方法中。
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        /**
         * 注意此时handleMessage()方法中的代码就是在主线程当中运行的了，所以我们可以放心地在这里进行UI操作。
         * 接下来对Message携带的what字段的值进行判断，如果等于UPDATE_TEXT，就将TextView显示的内容改成Nice to meet you。
         * */
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    //在这里进行UI操作
                    text.setText("异步线程😜 Nice To meet YOU");
                    break;
                default:
                    break;
            }
        }
    };

    public static void actionJumpToTestAndroidThreadActivity(Context context) {
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
        this.testThread01();
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
        } else if (v.getId() == R.id.test_android_thread_btn00_change_text_) {
            /**
             * Message是在线程之间传递的消息，它可以在内部携带少量的信息，用于在不同线程之间交换数据。
             * 上一小节中我们使用到了Message的what字段，除此之外还可以使用arg1和arg2字段来携带一些整型数据，使用obj字段携带一个Object对象。
             * */
            Message message = new Message();
            message.what = UPDATE_TEXT;
            handler.sendMessage(message);
        } else if (v.getId() == R.id.test_android_thread_task_btn01) {

        }
    }

    private void testThread02() {//异步任务
        Button btn00 = findViewById(R.id.test_android_thread_task_btn01);

        btn00.setOnClickListener(this);
    }
    private void testThread01() {//异步处理方法
        Button btn00 = findViewById(R.id.test_android_thread_btn00_change_text_);

        btn00.setOnClickListener(this);
    }

    private void testThread00() {//多线程处理方法
        text = findViewById(R.id.test_android_thread_text);
        Button btn = findViewById(R.id.test_android_thread_btn_change_text_);

        btn.setOnClickListener(this);
    }


}