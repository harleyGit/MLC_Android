package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ganghuang.mlc2_android.R;

import java.util.ArrayList;
import java.util.List;

public class TestMsgBubbleActivity extends AppCompatActivity {
    private List<TestMsgModel> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private TestMsgAdapter adapter;


    public static void actionStartOfTestMsgBubbleActivity(Context context) {//跳转到TestMsgBubbleActivity
        Intent intent = new Intent(context, TestMsgBubbleActivity.class);

        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_msg_bubble);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        initMsgs(); // 初始化消息数据
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);


        //LinearLayoutManager 是 Android 中的一个布局管理器，用于控制 RecyclerView 中子项的排列方式
        /**
         * LinearLayout 是一种布局容器，用于在布局 XML 文件中定义视图的排列方式。它将子视图按顺序垂直或水平排列。你可以在布局 XML 文件中定义一个 LinearLayout，然后在其中添加子视图。
         *
         *
         * LinearLayoutManager 是一种布局管理器，用于控制 RecyclerView 中子项的排列方式。它是通过 Java 代码创建的，用来设置 RecyclerView 的布局方式。
         *
         *
         *
         * 关系和区别
         * 布局层次不同：LinearLayout 是一种布局容器，可以在 XML 中定义布局；LinearLayoutManager 是一种布局管理器，用于在代码中控制 RecyclerView 中子项的布局。
         * 使用场景不同：LinearLayout 通常用于静态布局，直接在 XML 中定义；LinearLayoutManager 则用于动态列表布局，通常与 RecyclerView 一起使用。
         * 创建方式不同：LinearLayout 主要通过 XML 创建；LinearLayoutManager 是通过 Java 代码创建。
         *
         *
         * 类似的布局管理器
         * LinearLayoutManager：用于垂直或水平排列 RecyclerView 中的子项。
         * GridLayoutManager：用于在网格中排列 RecyclerView 中的子项。
         * StaggeredGridLayoutManager：用于实现错位网格布局。
         * FlexboxLayoutManager：用于实现类似 CSS 的 flexbox 布局。
         *
         *
         * 对比总结
         * LinearLayout：用于简单的静态布局，适合在 XML 中直接定义视图层次。
         * LinearLayoutManager：用于复杂的动态列表布局，通常与 RecyclerView 一起使用，通过代码进行配置和管理。
         * */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new TestMsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (! "".equals(content)) {
                    TestMsgModel msg = new TestMsgModel(content, TestMsgModel.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() -1); // 当有新消息时，
                    //刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size() -1); // 将
                    //RecyclerView定位到最后一行
                    inputText.setText(""); // 清空输入框中的内容
                }
            }
        });
    }

    private void initMsgs() {
        TestMsgModel msg1 = new TestMsgModel("Hello guy.", TestMsgModel.TYPE_RECEIVED);
        msgList.add(msg1);
        TestMsgModel msg2 = new TestMsgModel("Hello. Who is that? ", TestMsgModel.TYPE_SENT);
        msgList.add(msg2);
        TestMsgModel msg3 = new TestMsgModel("This is Tom. Nice talking to you. ", TestMsgModel.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}




