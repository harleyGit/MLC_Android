package com.ganghuang.mlc2_android.TestModules.TestGason;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TestProductViewModelFactory implements ViewModelProvider.Factory {
    private final String parameter;
    private final Context currentContext;

    public TestProductViewModelFactory(String parameter, Context currentContext) {
        this.parameter = parameter;
        this.currentContext = currentContext;
    }


    /**
     * 这个方法是 ViewModelProvider.Factory 接口的一部分，用于创建 ViewModel 实例，并且在初始化时可以传递自定义参数。
     * 具体来说，create 方法的主要作用是创建并返回指定类型的 ViewModel 实例。
     *
     * modelClass: Class<T> 类型的参数，用于指定要创建的 ViewModel 类型。
     * 这个参数由 ViewModelProvider 提供，它会传递你希望创建的 ViewModel 类的类型，例如 ProductViewModel.class。
     *
     *
     * T: 泛型类型，继承自 ViewModel，表示返回的 ViewModel 实例。
     *
     *
     * **/
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        /**
         * 这个检查用于确定传递的 modelClass 是否是 ProductViewModel 或其子类。
         * isAssignableFrom 方法可以确保类型安全性，避免传递错误的 ViewModel 类型。
         * */
        if (modelClass.isAssignableFrom(TestProductViewModel.class)) {
            return (T) new TestProductViewModel(parameter, this.currentContext);
        }
        /**
         * 如果传递的 modelClass 不是 ProductViewModel 或其子类，抛出 IllegalArgumentException 异常，提示未知的 ViewModel 类。
         * */
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}