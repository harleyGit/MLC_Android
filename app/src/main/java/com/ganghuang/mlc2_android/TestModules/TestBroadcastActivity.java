package com.ganghuang.mlc2_android.TestModules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;

public class TestBroadcastActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReciver;

    public static void actionStartOfTestBroadcastActivity(Context context) {//跳转到TestBroadcastActivity
        Intent intent = new Intent(context, TestBroadcastActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_broadcast);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        this.testBuildBroadcast();
    }

    private void testBuildBroadcast() {//创建一个广播类
        intentFilter = new IntentFilter();
        //因为当网络状态发生变化时，系统发出的正是一条值为android.net.conn.CONNECTIVITY_CHANGE的广播，也就是说我们的广播接收器想要监听什么广播，就在这里添加相应的action。
        intentFilter.addAction("android.net.conn.CONNECTIVITY CHANGE");

        networkChangeReciver = new NetworkChangeReceiver();
        /**
         * 创建了一个NetworkChangeReceiver的实例，然后调用registerReceiver()方法进行注册，
         * 将NetworkChangeReceiver的实例和IntentFilter的实例都传了进去，
         * 这样NetworkChange-Receiver就会收到所有值为android.net.conn.CONNECTIVITY_CHANGE的广播，也就实现了监听网络变化的功能。
         * */
        registerReceiver(networkChangeReciver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(networkChangeReciver);
    }

    //注册一个TestBroadcastActivity的内部类NetworkChangeReceiver
    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Toast.makeText(context, "📢🥱 NetworkChangeReceiver --network changes", Toast.LENGTH_SHORT).show();


            ConnectivityManager connectionManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "📢🥱 网络🛜可用network is available",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "📢🥱 网络🛜 network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    //    public 标准广播（Normal broadcasts） 标准广播（Normal broadcasts）
}




