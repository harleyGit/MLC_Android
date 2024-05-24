package com.ganghuang.mlc2_android.TestModules;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

import java.util.List;

public class TestFruitAdapter extends ArrayAdapter<TestFruit> {

    private int resourceId;

    //构造函数，用于将上下文、ListView子项布局的id和数据都传递进来
    public TestFruitAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<TestFruit> objects) {
        super(context, textViewResourceId, objects);

        this.resourceId = textViewResourceId;
    }

    /**
     * 重写了getView()方法，这个方法在每个子项被滚动到屏幕内的时候会被调用。
     * 在getView()方法中，首先通过getItem()方法得到当前项的Fruit实例，然后使用LayoutInflater来为这个子项加载我们传入的布局
     *
     *
     * 优化1:convertView参数，这个参数用于将之前加载好的布局进行缓存，以便之后可以进行重用。
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 获取当前项的Fruit实例
        TestFruit fruit = getItem(position);

        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            /**
             * LayoutInflater的inflate()方法接收3个参数，前两个参数我们已经知道是什么意思了，
             * 第三个参数指定成false，表示只让我们在父布局中声明的layout属性生效，但不会为这个View添加父布局，因为一旦View有了父布局之后，它就不能再添加到ListView中了。
             * 如果你现在还不能理解这段话的含义也没关系，只需要知道这是ListView中的标准写法就可以了，当你以后对View理解得更加深刻的时候，再来读这段话就没有问题了。
             * */
            view = LayoutInflater.from(getContext()).inflate(this.resourceId, parent, false);

            //优化2
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);//将viewholder存放在view中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取viewHolder
        }

        viewHolder.fruitName.setText(fruit.getName());
        viewHolder.fruitImage.setImageResource(fruit.getImageId());

        return view;
    }

//    内部类ViewHolder
    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }

    public static class TestTitleLayout extends LinearLayout {

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
            titleBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
            titleEdit.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "You clicked Edit button",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static class TestUILayoutActivityTest extends TestBaseActivity {

        public static void actionStartOfTestUILayoutActivity(Context context) {//跳转到TestUILayoutActivity
            Intent intent = new Intent(context, TestUILayoutActivityTest.class);

            context.startActivity(intent);
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_test_uilayout);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            this.testHideSystemNaviBar();

        }


        private  void  testHideSystemNaviBar(){//隐藏系统自带的导航栏
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null){
                actionBar.hide();
            }
        }

    }

    //通过实现 View.OnClickListener，可以监听按钮点击方法
    public static class TestUIWidgetActivity extends AppCompatActivity implements View.OnClickListener {

        private Button button00;
        private EditText editText;
        private ImageView imageView;
        private ProgressBar progressBar;

        public static void actionStartOfTestUIWidgetActivity(Context context) {//跳转到TestUIWidgetActivity
            Intent intent = new Intent(context, TestUIWidgetActivity.class);

            context.startActivity(intent);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_test_uiwidget);

            button00 = findViewById(R.id.uiwidget_button);
            button00.setOnClickListener(this);

            editText = findViewById(R.id.uiwidget_editText);

            imageView = findViewById(R.id.image_view1);

            progressBar = findViewById(R.id.progress_bar);


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        @Override
        public void onClick(View v) {

            if (R.id.uiwidget_button == v.getId()) {
                //this.testShowEditTextToas();
                //this.testExchangeImageView();
                //this.testProgressBarShow();
                //this.testShowAlertDialog();
                this.testProgressDialog();
            }

        }


        private void testProgressDialog() {//进度弹窗
            ProgressDialog progressDialog = new ProgressDialog(TestUIWidgetActivity.this);
            progressDialog.setTitle("This is ProgressDialog");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(true);//传入了false，表示ProgressDialog是不能通过Back键取消掉的，这时你就一定要在代码中做好控制，当数据加载完成后必须要调用ProgressDialog的dismiss()方法来关闭对话框，否则ProgressDialog将会一直存在
            progressDialog.show();

        }

        private void testShowAlertDialog() {//弹框处理
            AlertDialog.Builder dialog = new AlertDialog.Builder(TestUIWidgetActivity.this);
            dialog.setTitle("这是一个弹窗标题");
            dialog.setMessage("这个事物是重要的");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            dialog.show();
        }

        private void testProgressBarShow() {//加载视图是否可见
            if (progressBar.getVisibility() == View.GONE) {
                progressBar.setVisibility(View.VISIBLE);
            } else if (progressBar.getVisibility() == View.VISIBLE) {
                progressBar.setVisibility(View.GONE);
            }
        }

        private void testExchangeImageView() {//切换图片
            imageView.setImageResource(R.drawable.img_2);
        }

        private void testShowEditTextToas() {
            String inputText = editText.getText().toString();
            Toast.makeText(TestUIWidgetActivity.this, inputText, Toast.LENGTH_SHORT).show();
        }
    }

    public static class ThirdActivityTest extends TestBaseActivity {

        private Button button3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_third);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            button3 = findViewById(R.id.button_3);
            this.testButton3Click();

        }


        private  void  testButton3Click(){
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    this.testFinshActivity();
                }

                private void testFinshActivity() {
                    TestActivityCollector.finishAll();
                }
            });
        }
    }

    public static class TestModuleFunctionModel {
        private String functionId;
        private String functionName;
        private String functionPic;

        public String getFunctionId() {
            return functionId;
        }

        public void setFunctionId(String functionId) {
            this.functionId = functionId;
        }

        public String getFunctionName() {
            return functionName;
        }

        public void setFunctionName(String functionName) {
            this.functionName = functionName;
        }

        public String getFunctionPic() {
            return functionPic;
        }

        public void setFunctionPic(String functionPic) {
            this.functionPic = functionPic;
        }
    }
}
