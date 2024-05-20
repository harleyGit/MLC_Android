package com.ganghuang.mlc2_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends BaseActivity {

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private Button layoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        // 初始化ActivityResultLauncher
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String resultData = data.getStringExtra("resultKey");
                            Log.v("🍎返回的数据", resultData);
                        }
                    }
                });
        this.testBtnClickMethod();
        this.testPushToLayoutActivity();

        this.testBtnOfLifeCycleMethod();

    }


    public  void  testPushToLayoutActivity(){
        layoutBtn = findViewById(R.id.button_1_2);
        layoutBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TestUILayoutActivity.actionStartOfTestUILayoutActivity(FirstActivity.this);
            }
        });
    }

    public void testBtnOfLifeCycleMethod() {//生命周期方法
        Button button1_1 = findViewById(R.id.button_1_1);

        button1_1.setOnClickListener(new View.OnClickListener() {//BUTTON1方法
            @Override
            public void onClick(View v) {
                this.testJumpToActivityLifeCycle();
            }

            private void testJumpToActivityLifeCycle() {
                Intent intent = new Intent(FirstActivity.this, ActivityLifeCycle.class);
                startActivity(intent);
            }
        });
    }

    public void testBtnClickMethod() {//Button1跳转方法
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this.testShowJumpToSecondActivity();
                //this.testToastClick();
                //this.testHintJumpToSecondActivity();
                //this.testHintJumpToThirdActivity();
//                this.testHintJumpToDial();
                //this.testShowJumpToSeconActivityWithCallBackData();
                //this.testJumpToSecondActivity();
                this.testJumpToTestUIWidgetActivity();
            }


            private  void testJumpToTestUIWidgetActivity(){//跳转TestUIWidgetActivity
                TestUIWidgetActivity.actionStartOfTestUIWidgetActivity(FirstActivity.this);
            }
            private  void testJumpToSecondActivity(){//SecondActivity内部封装方法跳转
                SecondActivity.actionStart(FirstActivity.this, "参数1", "参数2");
            }
            private void testShowJumpToSeconActivityWithCallBackData() {//跳转SecondActivity并返回数据
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                activityResultLauncher.launch(intent);
            }

            private void testHintJumpToDial() {//拨打电话10086,注意tel不能丢失，否则无法拨打电话
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }

            private void testHintJumpToThirdActivity() {//跳转类似网页的ThirdActivity
                Intent intent = new Intent(Intent.ACTION_VIEW);//Android系统内 置 的 动 作 ， 其 常 量 值 次 a n d r o i d . i n t e n t . a c t i o n . V I E W
                //intent.setData (Uri. parse("http://www.baidu.com")) ;
                startActivity(intent);
            }

            private void testHintJumpToWeb() {//跳转网页
                Intent intent = new Intent(Intent.ACTION_VIEW);//Android系统内 置 的 动 作 ， 其 常 量 值 次 a n d r o i d . i n t e n t . a c t i o n . V I E W
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }

            private void testHintJumpToSecondActivity() {//隐示跳转到SecondActivity
                //FirstActivity.this作为上下文
                Intent intent = new Intent("android.intent.action.ACTION_START");
                //默认的Category可以不用加的
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);
            }

            private void testShowJumpToSecondActivity() {//显示跳转到SecondActivity
                String data = "Hello SecondActivity 🍎🍊";
                //FirstActivity.this作为上下文
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);//传递数据给下一个activity
                startActivity(intent);
            }

            void testToastClick() {//弹出弹框
                Toast.makeText(FirstActivity.this,
                        "you clicked Button 1",
                        Toast.LENGTH_SHORT).show();
            }

            private void testFinishActivityClick() {//结束生命活动
                finish();//当前的活动就被成功销毁
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        //R.menu.main 所在资源文件夹
        //getMenuInflater()方法能够得到MenuInflater 对象
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //这里不能用Switch否则会报错：在Android Studio中使用JDK17以上版本，会出现switch语句报错"Constant expression required"的问题，这是因为在JDK17中switch语句的条件表达式支持使用枚举类型，而这个特性还没有被支持
        if (item.getItemId() == R.id.add_item) {
            Toast.makeText(this, "you clicked Add", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.remove_item) {
            Toast.makeText(this, "you clicked Remove", Toast.LENGTH_SHORT).show();

        }
        return true;
    }
}