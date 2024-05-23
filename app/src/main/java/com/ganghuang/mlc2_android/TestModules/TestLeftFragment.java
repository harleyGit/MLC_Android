package com.ganghuang.mlc2_android.TestModules;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ganghuang.mlc2_android.R;

public class TestLeftFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //通过LayoutInflater的inflate()方法将刚才定义的left_fragment布局动态加载进来
        View view = inflater.inflate(R.layout.test_left_fragment_layout, container, false);
        return view;
    }
}


