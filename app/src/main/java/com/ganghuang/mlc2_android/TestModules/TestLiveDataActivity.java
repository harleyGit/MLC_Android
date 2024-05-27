package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ganghuang.mlc2_android.R;

public class TestLiveDataActivity extends AppCompatActivity {
    private TestMyViewModel counterViewModel;

    public static void actionJumpToTestLiveDataActivity(Context context) {
        Intent intent = new Intent(context, TestLiveDataActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_live_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.testLiveDataOfIncrement();
    }


    private void testLiveDataOfIncrement() {//测试LiveData的自动增加
        TextView counterTextView = findViewById(R.id.test_liveData_counterTextView);
        Button incrementButton = findViewById(R.id.test_liveData_incrementButton);

        // 获取 ViewModel 实例
        counterViewModel = new ViewModelProvider(this).get(TestMyViewModel.class);

        // 观察 LiveData 的变化, 通过 observe 方法观察 LiveData 的变化，当计数值发生变化时更新 TextView。
        counterViewModel.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                counterTextView.setText(String.valueOf(count));
            }
        });

        // 设置按钮点击监听器
        incrementButton.setOnClickListener(view -> counterViewModel.incrementCount());
    }


}