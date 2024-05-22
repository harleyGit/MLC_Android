package com.ganghuang.mlc2_android.TestModules;

public class TestMsgModel {
    public static final int TYPE_RECEIVED = 0;//收到消息类型
    public static final int TYPE_SENT = 1;//发送消息类型
    private String content;
    private int type;

    public TestMsgModel(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}

