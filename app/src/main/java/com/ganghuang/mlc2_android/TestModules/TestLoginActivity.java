package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

public class TestLoginActivity extends TestBaseActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;


    public  static void actionJumpToTestLoginActivity(Context context){
        Intent intent = new Intent(context, TestLoginActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        testLoginUILayout();
    }

    private  void testLoginUILayout(){
        accountEdit = (EditText) findViewById(R.id.test_login_account);
        passwordEdit = (EditText) findViewById(R.id.test_login_password);
        login = (Button) findViewById(R.id.test_login_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                // 如果账号是admin且密码是123456，就认为登录成功
                if (account.equals("admin") && password.equals("123456")) {
                    Intent intent = new Intent(TestLoginActivity.this, TestMainActivity.
                            class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(TestLoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

