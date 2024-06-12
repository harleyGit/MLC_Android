package com.ganghuang.mlc2_android.TestModules;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

public class TestSystemPermissionActivity extends AppCompatActivity implements View.OnClickListener {
    public static void actionJumpToTestSystemPermissionActivity(Context context) {//跳转到TestSystemPermissionActivity
        Intent intent = new Intent(context, TestSystemPermissionActivity.class);

        context.startActivity(intent);
    }

    private static final int PERMISSION_REQUEST_CODE_1 = 101;
    private static final int PERMISSION_REQUEST_CODE_2 = 102;
    private static final int PERMISSION_REQUEST_CODE_3 = 103;
    private static final int PERMISSION_REQUEST_CODE_4 = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_system_permission);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        testLocation();
    }


    private void testLocation() {//定位权限
        Button btn0 = findViewById(R.id.activity_test_system_permission_btn00);
        btn0.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_test_system_permission_btn00) {//资料： https://blog.csdn.net/weixin_45873353/article/details/133914710
            //发出请求确认本地是否有权限,返回结果为PackageManager.PERMISSION_GRANTED（同意）或者PackageManager.DENIED（不同意）
            //ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            //申请权限没有弹窗原因：在AndroidManifest.xml中没有加入显式的权限说明； Android版本比较新，有一些新的要求

            // 请求位置权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //如果请求结果为不允许，则需手动发出请求
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE_1);
            }
        }
    }

    private void requestPermissions() {

        /*
        // 申请计步器权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED
                && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, PERMISSION_REQUEST_CODE_2);
        }

        // 请求读写文件权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //多个权限一起申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE_4);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(intent);
            }
        }
        */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE_1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 用户已授予权限，执行您的逻辑
                    Log.d("PERMISSION", "位置权限已赋予");
                } else {
                    // 用户拒绝了权限，您可以在这里处理拒绝权限的逻辑
                    Log.d("PERMISSION", "位置权限已拒绝");
                }
                break;
            case PERMISSION_REQUEST_CODE_2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 用户已授予权限，执行您的逻辑
                    Log.d("PERMISSION", "运动权限已赋予");
                } else {
                    // 用户拒绝了权限，您可以在这里处理拒绝权限的逻辑
                    Log.d("PERMISSION", "运动权限已拒绝");
                }
                break;
            case PERMISSION_REQUEST_CODE_3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 用户已授予权限，执行您的逻辑
                    Log.d("PERMISSION", "卫星权限已赋予");
                } else {
                    // 用户拒绝了权限，您可以在这里处理拒绝权限的逻辑
                    Log.d("PERMISSION", "卫星权限已拒绝");
                }
                break;
            case PERMISSION_REQUEST_CODE_4:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 用户已授予权限，执行您的逻辑
                    Log.d("PERMISSION", "存储权限已赋予");
                } else {
                    // 用户拒绝了权限，您可以在这里处理拒绝权限的逻辑
                    Log.d("PERMISSION", "存储权限已拒绝");
                }
                break;
        }
    }



    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //返回结果为PackageManager.PERMISSION_GRANTED（同意）或者PackageManager.DENIED（不同意）
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 用户已授予权限，执行您的逻辑
            Log.d("PERMISSION", "位置权限已赋予");

        } else {
            // 用户拒绝了权限，您可以在这里处理拒绝权限的逻辑
            Log.d("PERMISSION", "位置权限已拒绝");
        }
    }
    */
}