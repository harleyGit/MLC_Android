package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ganghuang.mlc2_android.R;

public class TestFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    public static void actionStartOfTestFragmentActivity(Context context) {//跳转到TestFragmentActivity
        Intent intent = new Intent(context, TestFragmentActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_fragment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.testLeftAndRightFragment();
    }



    private  void  testLeftAndRightFragment(){

        Button button = findViewById(R.id.test_left_fragment_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (R.id.test_left_fragment_button == v.getId()){
            //new TestAnotherRightFragment() 创建待添加的碎片实例
            //replaceFragment(new TestAnotherRightFragment());

            //replaceFragmentOfStack(new TestAnotherRightFragment());
            this.getFragmentByID();
        }

    }

    private  void  getFragmentByID(){//在活动Activity中获取🧩碎片
        TestRightFragment rightFragment = (TestRightFragment) getSupportFragmentManager().findFragmentById(R.id.right_fragment);

        Log.v("🍎", "🍊rightFragment"+rightFragment);
    }
    private void  replaceFragment(Fragment fragment){//动态添加碎片
        //获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启一个事务，通过调用beginTransaction()方法开启
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //向容器内添加或替换碎片，一般使用replace()方法实现，需要传入容器的id和待添加的碎片实例。
        fragmentTransaction.replace(R.id.right_fragment, fragment);

        fragmentTransaction.commit();
    }



        private void replaceFragmentOfStack(Fragment fragment) {//碎片中模拟返回栈

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.right_layout, fragment);
            /**
             * addToBackStack 它可以接收一个名字用于描述返回栈的状态，一般传入null即可。现在重新运行程序，
             * 并点击按钮将AnotherRightFragment添加到活动中，然后按下Back键，你会发现程序并没有退出，而是回到了RightFragment界面，
             * 继续按下Back键，RightFragment界面也会消失，再次按下Back键，程序才会退出。
             * */
            transaction.addToBackStack(null);
            transaction.commit();
        }

}




