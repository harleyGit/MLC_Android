package com.ganghuang.mlc2_android.TestModules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TestBootCompleteReceiver extends BroadcastReceiver {

    /**
     * 我们在广播接收器的onReceive()方法中都只是简单地使用Toast提示了一段文本信息，当你真正在项目中使用到它的时候，就可以在里面编写自己的逻辑。
     * 需要注意的是，不要在onReceive()方法中添加过多的逻辑或者进行任何的耗时操作，因为在广播接收器中是不允许开启线程的，当onReceive()方法运行了较长时间而没有结束时，程序就会报错。
     * 因此广播接收器更多的是扮演一种打开程序其他组件的角色，比如创建一条状态栏通知，或者启动一个服务等，这几个概念我们会在后面的章节中学到。
     * */
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        Toast.makeText(context, "Boot Complete 🀄️定义广播通知", Toast.LENGTH_SHORT).show();

        // an Intent broadcast.
    }
}