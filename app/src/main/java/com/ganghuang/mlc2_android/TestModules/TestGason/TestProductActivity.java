package com.ganghuang.mlc2_android.TestModules.TestGason;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ganghuang.mlc2_android.R;
import com.ganghuang.mlc2_android.TestModules.FirstActivityTest;

import java.util.List;

public class TestProductActivity extends AppCompatActivity {
    private TestProductViewModel viewModel;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TestProductAdapter adapter;


    public  static void actionJumpToTestProductActivity(Context context){//跳转到TestProductActivity
        Intent intent = new Intent(context, TestProductActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        this.testDemoOfTestProductModel();
    }


    private  void testDemoOfTestProductModel(){
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TestProductAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        /**
         * TestProductViewModelFactory这个类创建的原因是为了给TestProductViewModel传递参数，暂时没有找到通过get(TestProductViewModel.class)携带参数的
         *
         * 创建 ViewModelFactory 并传递参数
         * */
        String parameter = "some parameter";
        TestProductViewModelFactory factory = new TestProductViewModelFactory(parameter, this);


        /** get(TestProductViewModel.class) 会触发TestProductViewModel的初始化方法
         *在 Android 的架构组件中，ViewModelProvider 的 get 方法用于获取一个 ViewModel 实例。当你调用 get(ProductViewModel.class) 时，Android 框架会根据需要创建或返回一个现有的 ProductViewModel 实例。
         *
         * 原理
         * ViewModelProvider：
         *
         * ViewModelProvider 是一个帮助类，用于管理和创建 ViewModel 实例。
         * 它保证了 ViewModel 实例在生命周期所有者（如 Activity 或 Fragment）重建时（例如屏幕旋转）不会被销毁，而是会继续存在，直到生命周期所有者被完全销毁。
         * get 方法：
         *
         * ViewModelProvider 的 get 方法用于获取指定类型的 ViewModel 实例。
         * 如果 ViewModel 尚未创建，ViewModelProvider 会创建一个新的实例。如果已经创建了实例，ViewModelProvider 会返回现有的实例。
         * 初始化触发：
         *
         * 当你调用 new ViewModelProvider(this).get(ProductViewModel.class) 时，如果这是第一次请求 ProductViewModel 实例，ViewModelProvider 会创建它，并调用其 init 方法（如果有的话）。
         * 这种方式确保了 ViewModel 在生命周期内是单例的，并且能够保持数据，避免不必要的数据重载和重计算。
         *
         **/
        viewModel = new ViewModelProvider(this, factory).get(TestProductViewModel.class);

        viewModel.getResult().observe(this, new Observer<Resource<Page<TestProduct>>>() {
            @Override
            public void onChanged(Resource<Page<TestProduct>> resource) {
                if (resource.getStatus() == Resource.Status.SUCCESS) {
                    updateUI(resource.getData().getItems());
                } else if (resource.getStatus() == Resource.Status.ERROR) {
                    showError(resource.getMessage());
                } else if (resource.getStatus() == Resource.Status.LOADING) {
                    showLoading();
                }
            }
        });
    }

    private void updateUI(List<TestProduct> products) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setProductList(products);
    }

    private void showError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();

    }

    private void showLoading() {
        // 显示加载指示器
        //Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }


}