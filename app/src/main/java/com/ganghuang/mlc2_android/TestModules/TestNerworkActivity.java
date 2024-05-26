package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestNerworkActivity extends AppCompatActivity implements View.OnClickListener {
    TextView responseText;

    public static void actionJumpToTestNerworkActivity(Context context) {//跳转到TestNerworkActivity
        Intent intent = new Intent(context, TestNerworkActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_nerwork);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.testGetMethodOfRequest();
        this.testOkhttpOfRequest();
    }


    private void testOkhttpOfRequest() {//okHttp库网络请求
        Button okhttpBtn = findViewById(R.id.test_network_send_request_okhttp_btn);
        okhttpBtn.setOnClickListener(this);
    }

    private void  sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .build();
                    Response response = client.newCall(request).execute();

                    String reponseData = response.body().string();
                    showResponse(reponseData);

                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }).start();
    }

    private void testGetMethodOfRequest() {//普通网络请求
        Button sendRequest = (Button) findViewById(R.id.test_network_send_request);
        responseText = findViewById(R.id.test_network_response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.test_network_send_request) {
            this.sendRequestWithHttpURLConnection();
        } else if (v.getId() == R.id.test_network_send_request_okhttp_btn) {
            this.sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpsURLConnection) url.openConnection();
                    /** POST请求
                     *  connection.setRequestMethod("POST");
                     *  DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                     *  out.writeBytes("username=admin&password=123456");
                     * */
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response) {

        //通过这个方法将线程切换到主线程，然后再更新UI元素。
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }
}



