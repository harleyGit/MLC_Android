package com.ganghuang.mlc2_android;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TestTitleLayout extends LinearLayout {

    /**
     * 重写了LinearLayout中带有两个参数的构造函数，在布局中引入TitleLayout控件就会调用这个构造函数。
     * 然后在构造函数中需要对标题栏布局进行动态加载，这就要借助LayoutInflater来实现了。
     * **/
    public TestTitleLayout(Context context, AttributeSet attrs) {
        super(context);

        /**
         * 调用inflate()方法就可以动态加载一个布局文件，inflate()方法接收两个参数，
         * 第一个参数是要加载的布局文件的id，这里我们传入R.layout.title，第二个参数是给加载好的布局再添加一个父布局，这里我们想要指定为TitleLayout，于是直接传入this。
         * */
        LayoutInflater.from(context).inflate(R.layout.title_layout, this);

        this.testLayoutConfigTitleContent();

    }


    private  void testLayoutConfigTitleContent(){
        Button titleBack = (Button) findViewById(R.id.title_back);
        Button titleEdit = (Button) findViewById(R.id.title_edit);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        titleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked Edit button",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}



