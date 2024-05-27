package com.ganghuang.mlc2_android.TestModules;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestMyViewModel extends ViewModel {

    private final MutableLiveData<Integer> count = new MutableLiveData<>(0);

    /**
     * LiveData 是 Android 架构组件库（Architecture Components）中的一个类，属于系统库中的一部分。它是一个持有数据并具有生命周期感知能力的数据容器类。
     * LiveData 的主要作用是帮助开发者处理与界面生命周期相关的数据更新问题，确保 UI 组件在其生命周期内安全地访问数据。
     *
     * LiveData 的功能和特点
     * 持有数据：LiveData 持有某种类型的数据，可以通过观察者模式来获取数据的变化。
     *
     * 生命周期感知：LiveData 具有生命周期感知能力，它可以自动管理与观察者之间的关系，只有当观察者（通常是 Activity 或 Fragment）处于活跃状态时，LiveData 才会通知观察者数据的变化。
     *
     * 自动停止更新：当观察者（比如 Activity 或 Fragment）进入非活跃状态（比如进入后台或销毁），LiveData 会自动停止发送更新通知，避免潜在的内存泄漏和无用的计算。
     *
     * 数据变化通知：当数据发生变化时，LiveData 会通知所有活跃的观察者，使 UI 组件能够及时更新。
     * */
    public LiveData<Integer> getCount() {
        return count;
    }

    public void incrementCount() {
        if (count.getValue() != null) {
            count.setValue(count.getValue() + 1);
        }
    }
}


