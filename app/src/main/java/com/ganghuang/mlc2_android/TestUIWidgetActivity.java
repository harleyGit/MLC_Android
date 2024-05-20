package com.ganghuang.mlc2_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


//通过实现 View.OnClickListener，可以监听按钮点击方法
public class TestUIWidgetActivity extends AppCompatActivity implements View.OnClickListener {

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