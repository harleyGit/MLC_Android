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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestFilepersistenceActivity extends AppCompatActivity {

    private EditText edit;


    public static void actionJumpToTestFilepersistenceActivity(Context context) {//跳转到TestFilepersistenceActivity
        Intent intent = new Intent(context, TestFilepersistenceActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_filepersistence);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        this.testSaveEditText();
        this.testShowToastOfReadData();
    }

    private  void testShowToastOfReadData(){
        String readTxt = this.load();

        Button btn = findViewById(R.id.test_file_persistence_btn0);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestFilepersistenceActivity.this,readTxt, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void testSaveEditText() {
        edit = findViewById(R.id.test_file_persistence_edit);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = this.edit.getText().toString();
        save(inputText);
    }

    public void save(String inputText) {//写入文本到

        /**
         * 导航栏-》View-》Tool Windows-》Device Explore-》data/data/你自己的包名
         * 比如我的MLC2_Android的包名是：com.ganghuang.mlc2_android
         *
         *
         */
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            /**
             * MODE_PRIVATE和MODE_APPEND。
             * 其中MODE_PRIVATE是默认的操作模式，表示当指定同样文件名的时候，所写入的内容将会覆盖原文件中的内容，而MODE_APPEND则表示如果该文件已存在，就往文件里面追加内容，不存在就创建新文件。
             * */
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}




