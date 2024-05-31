package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

public class TestUILayoutActivity extends AppCompatActivity {

    public static void actionJumpToTestUILayoutActivity(Context context) {//跳转到TestUILayoutActivity
        Intent intent = new Intent(context, TestUILayoutActivity.class);

        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        /**
         * setContentView里的内容‘R’为就是系统给声明的静态变量，所有的子类都是通过‘R’来调用。
         * 而‘R’的路径则是 Android 工程路径下的 app -> src -> main -> res 下。
         *
         * R.layout指res文件夹下一个名为layout的文件夹，而R.layout.activity_linear_layout之后的部分则是layout文件夹下自己定义的.xml文件。
         * */
        setContentView(R.layout.activity_test_uilayout2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}