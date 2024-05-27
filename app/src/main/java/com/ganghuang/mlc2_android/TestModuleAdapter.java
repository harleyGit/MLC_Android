package com.ganghuang.mlc2_android;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ganghuang.mlc2_android.TestModules.ActivityLifeCycle;
import com.ganghuang.mlc2_android.TestModules.FirstActivityTest;
import com.ganghuang.mlc2_android.TestModules.SecondActivityTest;
import com.ganghuang.mlc2_android.TestModules.TestAndroidThreadActivity;
import com.ganghuang.mlc2_android.TestModules.TestBroadcastActivity;
import com.ganghuang.mlc2_android.TestModules.TestFilepersistenceActivity;
import com.ganghuang.mlc2_android.TestModules.TestFragmentActivity;
import com.ganghuang.mlc2_android.TestModules.TestFruitAdapter;
import com.ganghuang.mlc2_android.TestModules.TestListViewActivity;
import com.ganghuang.mlc2_android.TestModules.TestLiveDataActivity;
import com.ganghuang.mlc2_android.TestModules.TestLoginActivity;
import com.ganghuang.mlc2_android.TestModules.TestMsgBubbleActivity;
import com.ganghuang.mlc2_android.TestModules.TestNerworkActivity;
import com.ganghuang.mlc2_android.TestModules.TestRuntimePermissionActivity;

import java.util.List;

class TestModuleViewHolder extends RecyclerView.ViewHolder {
    ImageView moduleImage;
    TextView moduleName;
    View moduleItemView;


    /**
     * View参数，这个参数通常就是RecyclerView子项的最外层布局，
     * 那么我们就可以通过findViewById()方法来获取到布局中的ImageView和TextView的实例了。
     */
    public TestModuleViewHolder(View itemView) {
        super(itemView);
        this.moduleItemView = itemView;
        moduleImage = itemView.findViewById(R.id.test_module_image);
        moduleName = itemView.findViewById(R.id.test_module_name);
    }
}

public class TestModuleAdapter extends RecyclerView.Adapter<TestModuleViewHolder> {

    private List<TestFruitAdapter.TestModuleFunctionModel> modelList;
    Context context;

    public TestModuleAdapter(@NonNull Context context, List<TestFruitAdapter.TestModuleFunctionModel> functionList) {
        modelList = functionList;
        this.context = context;
    }

    @NonNull
    @Override
    public TestModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_module_layout, parent, false);
        //把加载出来的布局传入到构造函数当中，最后将ViewHolder的实例返回。
        TestModuleViewHolder holder = new TestModuleViewHolder(view);
        holder.moduleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getBindingAdapterPosition();
                TestFruitAdapter.TestModuleFunctionModel model = modelList.get(position);
                Toast.makeText(view.getContext(), "你点击了图片 " + model.getFunctionName(), Toast.LENGTH_SHORT).show();

                // 通过外部类引用调用 testAA 方法
                TestModuleAdapter.this.testAA();
            }

        });
        holder.moduleItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getBindingAdapterPosition();
                TestFruitAdapter.TestModuleFunctionModel model = modelList.get(position);
                Toast.makeText(view.getContext(), "你点击了 " + model.getFunctionName(), Toast.LENGTH_SHORT).show();

                Context currentContext = TestModuleAdapter.this.context;
                /**
                 * TestModuleAdapter.this.testAA()：这里的 TestModuleAdapter.this 引用的是 TestModuleAdapter 类的实例。
                 * 它确保你调用的是外部类的 testAA 方法，而不是内部类 View.OnClickListener 中的某个方法。
                 *
                 *
                 * 匿名内部类：new View.OnClickListener() 创建了一个匿名内部类实例。
                 * 在这个内部类中，this 关键字指的是这个内部类的实例，而不是外部类 TestModuleAdapter 的实例。
                 *
                 * 总结
                 * 使用 TestModuleAdapter.this 是为了确保在匿名内部类中能够正确引用外部类的实例和方法。
                 * 这样做的主要目的是解决作用域问题，因为在内部类中，this 关键字默认指向的是内部类的实例。
                 */
                if (model.getFunctionId().equals("firstActivity202405211620")) {
                    TestModuleAdapter.this.testJumpToFirstActivity();
                } else if (model.getFunctionId().equals("activityLifeCycle202405211621")) {
                    TestModuleAdapter.this.testJumpToActivityLifeCycle();
                } else if (model.getFunctionId().equals("secondActivity202405211804")) {
                    TestModuleAdapter.this.testShowJumpToSecondActivity();
                } else if (model.getFunctionId().equals("uilayoutActivity202405211802")) {
                    TestFruitAdapter.TestUILayoutActivityTest.actionStartOfTestUILayoutActivity(TestModuleAdapter.this.context);
                } else if (model.getFunctionId().equals("widgetActivity202405211803")) {
                    TestModuleAdapter.this.testJumpToTestUIWidgetActivity();
                } else if (model.getFunctionId().equals("listView202405211801")) {
                    TestListViewActivity.actionStartOfTestListViewActivity(TestModuleAdapter.this.context);
                } else if (model.getFunctionId().equals("simpleBubble202405211804")) {
                    TestMsgBubbleActivity.actionStartOfTestMsgBubbleActivity(TestModuleAdapter.this.context);
                } else if (model.getFunctionId().equals("fragment202405211804")) {
                    TestFragmentActivity.actionStartOfTestFragmentActivity(TestModuleAdapter.this.context);
                } else if (model.getFunctionId().equals("broadcastReceiver202405211804")) {
                    TestBroadcastActivity.actionStartOfTestBroadcastActivity(TestModuleAdapter.this.context);
                }else if(model.getFunctionId().equals("broadcastReceiverForceOffLine202405211804")){
                    TestLoginActivity.actionJumpToTestLoginActivity(TestModuleAdapter.this.context);
                }else if(model.getFunctionId().equals("filePersistenceActivity202405211804")){
                    TestFilepersistenceActivity.actionJumpToTestFilepersistenceActivity(currentContext);
                }else if(model.getFunctionId().equals("runtimePermission202405261011")){
                    TestRuntimePermissionActivity.actionJumpToTestRuntimePermissionActivity(currentContext);
                } else if (model.getFunctionId().equals("netWork202405261012")) {
                    TestNerworkActivity.actionJumpToTestNerworkActivity(currentContext);
                } else if (model.getFunctionId().equals("thread202405261013")) {
                    TestAndroidThreadActivity.actionJumpToTestAndroidThreadActivity(currentContext);
                }else if(model.getFunctionId().equals("liveData202405261014")){
                    TestLiveDataActivity.actionJumpToTestLiveDataActivity(currentContext);
                }
            }
        });
        return holder;
    }


    private  void testJumpToTestUIWidgetActivity(){//跳转TestUIWidgetActivity(简单组件)
        TestFruitAdapter.TestUIWidgetActivity.actionStartOfTestUIWidgetActivity(this.context);
    }
    private void testShowJumpToSecondActivity() {//显示跳转到SecondActivity
        String data = "Hello SecondActivity 🍎🍊";
        //FirstActivity.this作为上下文
        Intent intent = new Intent(this.context, SecondActivityTest.class);
        intent.putExtra("extra_data", data);//传递数据给下一个activity
        this.context.startActivity(intent);
    }
    private void testJumpToActivityLifeCycle() {//跳转到ActivityLifeCycle
        Intent intent = new Intent(this.context, ActivityLifeCycle.class);
        this.context.startActivity(intent);
    }
    private void testJumpToFirstActivity() {//跳转到FirstActivity
        FirstActivityTest.actionJumpToFirstActivity(this.context);
    }

    private void testAA() {
        Log.d("🍎", "在外卖哪哦哦那个");
    }

    /**
     * 用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，
     * 这里我们通过position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的ImageView和TextView当中即可。
     */
    @Override
    public void onBindViewHolder(@NonNull TestModuleViewHolder holder, int position) {

        TestFruitAdapter.TestModuleFunctionModel fruit = modelList.get(position);
        //动态获取图片资源
        int picId = this.context.getResources().getIdentifier(fruit.getFunctionPic(), "drawable", this.context.getPackageName());
        holder.moduleImage.setImageResource(picId);
        holder.moduleName.setText(fruit.getFunctionName());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

}

