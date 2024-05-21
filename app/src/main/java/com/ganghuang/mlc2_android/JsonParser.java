package com.ganghuang.mlc2_android;

import android.content.Context;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonParser {

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            is.close();
            json = stringBuilder.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static List<TestModuleFunctionModel> parseJson(Context context, String fileName) {
        String json = loadJSONFromAsset(context, fileName);
        if (json != null) {
            return JSON.parseArray(json, TestModuleFunctionModel.class);
        }
        return null;
    }


    public static List<TestModuleFunctionModel> TestReadJson(Context context, String fileName) {
        List<TestModuleFunctionModel> modelRess;
        try {
            //InputStreamReader 将字节输入流转换为字符流
            //注意：address.json 是因人而异的
            InputStreamReader isr = new InputStreamReader(context.getAssets().open(fileName), "UTF-8");
            //包装字符流,将字符流放入缓存里
            BufferedReader br = new BufferedReader(isr);
            String line;
            //StringBuilder和StringBuffer功能类似,存储字符串
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                //append 被选元素的结尾(仍然在内部)插入指定内容,缓存的内容依次存放到builder中
                builder.append(line);
            }
            br.close();
            isr.close();

            //builder.toString() 返回表示此序列中数据的字符串 (就是json串，后面自行解析就行)
            //这里我用的是fastJson,具体解析方式自行决定就好,数据格式也自行决定就好
            modelRess = JSON.parseArray(builder.toString(), TestModuleFunctionModel.class);
            for (int i = 0; i < modelRess.size(); i++) {
                TestModuleFunctionModel model = modelRess.get(i);
                System.out.println("-----------------");
                System.out.println("🍎 name= " + model.getFunctionName());

            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return modelRess;
    }
}
