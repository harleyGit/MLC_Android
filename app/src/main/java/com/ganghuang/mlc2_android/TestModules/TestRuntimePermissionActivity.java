package com.ganghuang.mlc2_android.TestModules;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

public class TestRuntimePermissionActivity extends AppCompatActivity {

    public static void actionJumpToTestRuntimePermissionActivity(Context context) {//跳转到TestRuntimePermissionActivity
        Intent intent = new Intent(context, TestRuntimePermissionActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_runtime_permission);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        this.testCallPhoneOfPermission();
    }


    private  void testCallPhoneOfPermission(){//打电话权限测试
        Button makeCall = (Button) findViewById(R.id.test_runtime_permission_make_call);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 运行时权限的核心就是在程序运行过程中由用户授权我们去执行某些危险操作，程序是不可以擅自做主去执行这些危险操作的。
                 * 因此，第一步就是要先判断用户是不是已经给过我们授权了，借助的是ContextCompat.checkSelfPermission()方法。
                 * checkSelfPermission()方法接收两个参数，第一个参数是Context，第二个参数是具体的权限名，比如打电话的权限名就是Manifest.permission.CALL_PHONE，
                 * 然后我们使用方法的返回值和PackageManager. PERMISSION_GRANTED做比较，相等就说明用户已经授权，不等就表示用户没有授权。
                 * */
                if (ContextCompat.checkSelfPermission(TestRuntimePermissionActivity.this, Manifest.
                        permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    /**
                     * 如果没有授权的话，则需要调用ActivityCompat.
                     * requestPermissions()方法来向用户申请授权，requestPermissions()方法接收3个参数，第一个参数要求是Activity的实例，第二个参数是一个String数组，我们把要申请的权限名放在数组中即可，第三个参数是请求码，只要是唯一值就可以了，这里传入了1。
                     * */
                    ActivityCompat.requestPermissions(TestRuntimePermissionActivity.this, new
                            String[]{ Manifest.permission.CALL_PHONE }, 1);
                } else {
                    call();
                }
            }
        });
    }
    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    /**
     * 调用完了requestPermissions()方法之后，系统会弹出一个权限申请的对话框，然后用户可以选择同意或拒绝我们的权限申请，不论是哪种结果，最终都会回调到onRequest-PermissionsResult()方法中，而授权的结果则会封装在grantResults参数当中。
     * 这里我们只需要判断一下最后的授权结果，如果用户同意的话就调用call()方法来拨打电话，如果用户拒绝的话我们只能放弃操作，并且弹出一条失败提示。
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.
                        PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}



