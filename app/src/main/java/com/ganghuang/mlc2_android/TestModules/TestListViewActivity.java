package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

import java.util.ArrayList;
import java.util.List;

public class TestListViewActivity extends AppCompatActivity {

    private List<TestFruit> fruitList = new ArrayList<>();
    private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
            "Pineapple", "Strawberry", "Cherry", "Mango"};

    public static void actionStartOfTestListViewActivity(Context context) {//跳转到TestListViewActivity
        Intent intent = new Intent(context, TestListViewActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //this.testLoadDataOfSysteItemView();
        this.testLoadDataOfCustomItemView();
    }


    public void testLoadDataOfCustomItemView() {//自定义的ItemView显示加载数据
        initFruits();
        TestFruitAdapter fruitAdapter = new TestFruitAdapter(TestListViewActivity.this, R.layout.test_fruit_item, this.fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TestFruit fruit = fruitList.get(position);
                Toast.makeText(TestListViewActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(fruitAdapter);
    }

    public void testLoadDataOfSysteItemView() {//使用系统的ItemView不是自定义的
        /**
         * android.R.layout.simple_list_item_1作为ListView子项布局的id，这是一个Android内置的布局文件，里面只有一个TextView，可用于简单地显示一段文本。这样适配器对象就构建好了
         * */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TestListViewActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            TestFruit apple = new TestFruit("Apple", R.drawable.apple);
            fruitList.add(apple);
            TestFruit banana = new TestFruit("Banana", R.drawable.banana);
            fruitList.add(banana);
            TestFruit orange = new TestFruit("Orange", R.drawable.orange);
            fruitList.add(orange);
            TestFruit watermelon = new TestFruit("Watermelon", R.drawable.watermelon);
            fruitList.add(watermelon);
            TestFruit pear = new TestFruit("Pear", R.drawable.pear);
            fruitList.add(pear);
            TestFruit grape = new TestFruit("Grape", R.drawable.grape);
            fruitList.add(grape);
            TestFruit pineapple = new TestFruit("Pineapple", R.drawable.pineapple);
            fruitList.add(pineapple);
            TestFruit strawberry = new TestFruit("Strawberry", R.drawable.strawberry);
            fruitList.add(strawberry);
            TestFruit cherry = new TestFruit("Cherry", R.drawable.cherry);
            fruitList.add(cherry);
            TestFruit mango = new TestFruit("Mango", R.drawable.mango);
            fruitList.add(mango);
        }
    }
}


