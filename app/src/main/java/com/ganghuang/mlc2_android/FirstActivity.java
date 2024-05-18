package com.ganghuang.mlc2_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //this.testShowJumpToSecondActivity();
                //this.testToastClick();
                //this.testHintJumpToSecondActivity();
                this.testHintJumpToThirdActivity();
            }


            private void testHintJumpToThirdActivity(){//跳转类似网页的ThirdActivity
                Intent intent = new Intent (Intent.ACTION_VIEW);//Android系统内 置 的 动 作 ， 其 常 量 值 次 a n d r o i d . i n t e n t . a c t i o n . V I E W
                //intent.setData (Uri. parse("http://www.baidu.com")) ;
                startActivity(intent);
            }

            private void testHintJumpToWeb(){//跳转网页
                Intent intent = new Intent (Intent.ACTION_VIEW);//Android系统内 置 的 动 作 ， 其 常 量 值 次 a n d r o i d . i n t e n t . a c t i o n . V I E W
                intent. setData (Uri. parse("http://www.baidu.com")) ;
                startActivity(intent);
            }
            private  void testHintJumpToSecondActivity(){//隐示跳转到SecondActivity
                //FirstActivity.this作为上下文
                Intent intent = new Intent("android.intent.action.ACTION_START");
                //默认的Category可以不用加的
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);
            }
            private  void testShowJumpToSecondActivity(){//显示跳转到SecondActivity
                //FirstActivity.this作为上下文
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
            void testToastClick(){//弹出弹框
                Toast.makeText(FirstActivity.this,
                        "you clicked Button 1",
                        Toast.LENGTH_SHORT).show();
            }

            private void testFinishActivityClick(){//结束生命活动
                finish();//当前的活动就被成功销毁
            }
        });
    }



    public boolean onCreateOptionsMenu(Menu menu){
        //R.menu.main 所在资源文件夹
        //getMenuInflater()方法能够得到MenuInflater 对象
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //这里不能用Switch否则会报错：在Android Studio中使用JDK17以上版本，会出现switch语句报错"Constant expression required"的问题，这是因为在JDK17中switch语句的条件表达式支持使用枚举类型，而这个特性还没有被支持
        if (item.getItemId() == R.id.add_item){
            Toast.makeText(this, "you clicked Add", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.remove_item) {
            Toast.makeText(this, "you clicked Remove", Toast.LENGTH_SHORT).show();

        }
        return true;
    }
}