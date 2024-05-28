package com.ganghuang.mlc2_android.TestModules.TestGason;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ganghuang.mlc2_android.R;

import java.util.ArrayList;
import java.util.List;

public class TestProductAdapter extends RecyclerView.Adapter<TestProductAdapter.ProductViewHolder> {

    private List<TestProduct> productList = new ArrayList<>();

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item_product_layout, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        TestProduct product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(String.valueOf(product.getCurrentPrice()));

        //Glide是一个图片加载库
        // 使用 Glide 加载图片
        Glide.with(holder.itemView.getContext())
                .load(product.getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setProductList(List<TestProduct> products) {
        this.productList = products;

        /**
         * notifyDataSetChanged() 是 Android 开发中 RecyclerView.Adapter 或 ListView.Adapter 中的一个重要方法。它的主要作用是通知附加的 Adapter 数据集已经更改，并且任何显示该数据集的视图都应该刷新自己。
         *
         * notifyDataSetChanged() 的主要用途
         * 更新用户界面：当数据集发生变化（例如，添加、删除或修改了项目）时，UI 需要反映这些变化。notifyDataSetChanged() 强制适配器重新绑定并重绘数据集中的所有项目，从而更新用户界面。
         * 数据同步：如果由于用户操作或后台操作（例如从服务器获取数据）动态更新数据集，调用此方法可以确保显示的列表始终与实际数据同步。
         * notifyDataSetChanged() 的工作原理
         * 调用 notifyDataSetChanged() 时，适配器会触发一系列方法调用，导致 RecyclerView 或 ListView 显示的视图被重新创建。这个过程可以分解为以下步骤：
         *
         * 数据无效化通知：适配器接收到数据已更改的通知，标记当前数据集无效。
         * 重新绑定数据：适配器会调用必要的方法，将新的数据绑定到视图上。
         * 重绘视图：适配器将重新创建并绘制所有可见的视图，以反映最新的数据。
         *
         * notifyDataSetChanged() 会被调用以刷新 RecyclerView
         * */
        notifyDataSetChanged();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        ImageView imageView;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.product_name);
            priceTextView = itemView.findViewById(R.id.product_price);
            imageView = itemView.findViewById(R.id.product_image);
        }
    }
}