package com.ganghuang.mlc2_android.TestModules;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class TestActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static  void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public  static  void  finishAll(){
        for (Activity activity: activities){
            if(! activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
