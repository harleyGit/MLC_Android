package com.ganghuang.mlc2_android;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class TestModuleViewHolder extends RecyclerView.ViewHolder {
    ImageView moduleImage;
    TextView moduleName;


    /**
     * View参数，这个参数通常就是RecyclerView子项的最外层布局，
     * 那么我们就可以通过findViewById()方法来获取到布局中的ImageView和TextView的实例了。
     */
    public TestModuleViewHolder(View itemView) {
        super(itemView);
        moduleImage = itemView.findViewById(R.id.test_module_image);
        moduleName = itemView.findViewById(R.id.test_module_name);
    }
}

public class TestModuleAdapter extends RecyclerView.Adapter<TestModuleViewHolder> {

    private List<TestModuleFunctionModel> modelList;
    Context context;
    public TestModuleAdapter(@NonNull Context context, List<TestModuleFunctionModel> functionList) {
        modelList = functionList;
        this.context = context;
    }

    @NonNull
    @Override
    public TestModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_module_layout, parent, false);
        //把加载出来的布局传入到构造函数当中，最后将ViewHolder的实例返回。
        TestModuleViewHolder holder = new TestModuleViewHolder(view);
        return holder;
    }

    /**
     * 用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，
     * 这里我们通过position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的ImageView和TextView当中即可。
     * */
    @Override
    public void onBindViewHolder(@NonNull TestModuleViewHolder holder, int position) {

        TestModuleFunctionModel fruit = modelList.get(position);
        //动态获取图片资源
        int picId = this.context.getResources().getIdentifier(fruit.getFunctionPic(), "drawable", this.context.getPackageName());
        holder.moduleImage.setImageResource(picId);
        holder.moduleName.setText(fruit.getFunctionName());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
    
}

