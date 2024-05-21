package com.ganghuang.mlc2_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestFruitAdapter extends ArrayAdapter<TestFruit> {

    private int resourceId;

    //构造函数，用于将上下文、ListView子项布局的id和数据都传递进来
    public TestFruitAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<TestFruit> objects) {
        super(context, textViewResourceId, objects);

        this.resourceId = textViewResourceId;
    }

    /**
     * 重写了getView()方法，这个方法在每个子项被滚动到屏幕内的时候会被调用。
     * 在getView()方法中，首先通过getItem()方法得到当前项的Fruit实例，然后使用LayoutInflater来为这个子项加载我们传入的布局
     *
     *
     * 优化1:convertView参数，这个参数用于将之前加载好的布局进行缓存，以便之后可以进行重用。
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 获取当前项的Fruit实例
        TestFruit fruit = getItem(position);

        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            /**
             * LayoutInflater的inflate()方法接收3个参数，前两个参数我们已经知道是什么意思了，
             * 第三个参数指定成false，表示只让我们在父布局中声明的layout属性生效，但不会为这个View添加父布局，因为一旦View有了父布局之后，它就不能再添加到ListView中了。
             * 如果你现在还不能理解这段话的含义也没关系，只需要知道这是ListView中的标准写法就可以了，当你以后对View理解得更加深刻的时候，再来读这段话就没有问题了。
             * */
            view = LayoutInflater.from(getContext()).inflate(this.resourceId, parent, false);

            //优化2
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);//将viewholder存放在view中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取viewHolder
        }

        viewHolder.fruitName.setText(fruit.getName());
        viewHolder.fruitImage.setImageResource(fruit.getImageId());

        return view;
    }

//    内部类ViewHolder
    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
}