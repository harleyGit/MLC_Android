package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.Activity;

import com.ganghuang.mlc2_android.R;

public class SecondActivityTest extends TestBaseActivity {

    private Button button2;

    public  static void actionStart(Context context, String data1, String data2){//跳转到SecondActivit
        Intent intent = new Intent(context, SecondActivityTest.class);
        intent.putExtra("param1", data1);
        intent.putExtra("parma2", data2);

        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        button2 = findViewById(R.id.button_2);
        this.testButton2Click();

        // this.getLastActivityData();
        this.configVideoPlayView();


        //用于处理窗口插入（Window Insets）的方法。窗口插入是指系统 UI 元素（如状态栏和导航栏）在应用窗口中的显示方式，以及这些 UI 元素占据的屏幕区域
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            //获取系统栏（状态栏和导航栏）的插入信息
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // 设置视图的内边距，以避免系统栏遮挡内容
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            // 返回原始的 insets 对象，以便其他处理可以继续
            return insets;
        });

        // 设置全屏模式，隐藏状态栏和导航栏
        hideSystemUI();
    }


    private void testButton2Click() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //this.getLastActivityDataAndBackLastActivity();
                this.testShowJumpToThirdActivity();
            }


            private void testShowJumpToThirdActivity() {//跳转类似网页的ThirdActivity
                Intent intent = new Intent(SecondActivityTest.this, TestFruitAdapter.ThirdActivityTest.class);
                startActivity(intent);
            }

            private void getLastActivityDataAndBackLastActivity() {//获取上一个Activity数据并返回
                Intent resultIntent = new Intent();
                /**
                 * 第 一 个 参 数 用 于 向 上 一个 活 动 返 回 处 理 结 果 ， 一 般 只 使 用 RESULT_OK或RESULT_CANCELED这两个值，
                 * 第二个参数则把带有数据的Intent传递回去，然后调用了finish()方法来销毁当前活 动
                 * */
                resultIntent.putExtra("resultKey", "Some Result Data🐝😡🐝🐝");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });



    }


    private void getLastActivityData() {//获取上一个Activity的数据
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d("🍎SecondActivity", data);
    }

    private void configVideoPlayView() {//视频播放视图
        // 获取VideoView
        VideoView videoView = findViewById(R.id.videoView);
        // 设置VideoView播放视频（这里假设有一个视频资源）
        //然后通过 getPackageName() 获取应用包名，并结合 R.raw.sample_video 构建视频的路径
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + "R.raw.sample_video");
        videoView.start();
    }


    // 隐藏系统UI（状态栏和导航栏）
    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }
}