package com.ganghuang.mlc2_android.TestModules.TestGason;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ganghuang.mlc2_android.TestModules.JsonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class TestProductViewModel extends ViewModel {

    /**
     * MediatorLiveData 是 Android Architecture Components 中的一部分，它扩展了 LiveData 类，并提供了合并多个 LiveData 源的功能。
     * MediatorLiveData 可以观察其他 LiveData 对象，并在这些 LiveData 对象的数据发生变化时，触发自身的观察者。
     *
     *
     * Resource 是一种常见的包装类，用于封装资源的状态、数据和错误信息。
     * 通常在网络请求或数据库查询时使用，以便更好地管理加载状态和错误处理。
     *
     * MediatorLiveData<>：这是一个 LiveData 子类，可以观察其他 LiveData 对象。
     * Resource<Page<Product>>：这是泛型类型参数，表示 MediatorLiveData 将持有的值是一个包含分页数据 Page 和商品信息 Product 的资源 Resource。
     *
     * 定义了这个 MediatorLiveData 对象，并且需要从仓库获取数据，更新 result 的值
     * */
    private MediatorLiveData<Resource<Page<TestProduct>>> result = new MediatorLiveData<>();
    private ProductRepository repository = new ProductRepository();


    private String parameter;
    private Context currentContext;

    public TestProductViewModel(String parameter, Context context) {
        this.parameter = parameter;
        this.currentContext = context;
        loadProducts();
    }

    public TestProductViewModel() {//初始化方法
        loadProducts();
    }

    private void loadProducts() {
        LiveData<Resource<Page<TestProduct>>> source = repository.getProducts(this.currentContext);
        result.addSource(source, resource -> {
            result.setValue(resource);
            result.removeSource(source); // 如果只需要加载一次数据，可以移除source
        });
    }

    public LiveData<Resource<Page<TestProduct>>> getResult() {
        return result;
    }
}


class ProductRepository {

    public LiveData<Resource<Page<TestProduct>>> getProducts(Context currentContext) {
        MutableLiveData<Resource<Page<TestProduct>>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        // 假设我们从网络请求中获取 JSON 数据, 这个数据有点问题，字段不太对 😂哈哈
        //String jsonResponse00 = "{ \"items\": [{\"name\": \"Product1\", \"price\": 100.0},{\"name\": \"Product2\", \"price\": 150.0}], \"currentPage\": 1, \"totalPages\": 10}";;

        String jsonResponse = JsonParser.loadJSONFromAsset(currentContext, "TestJson00.json");


        // 使用 Gson 解析 JSON 数据
        Gson gson = new Gson();

        /**
         * TypeToken 是 Gson 库中的一个工具类，用于解决 Java 泛型类型擦除问题。
         * 在 Java 中，泛型类型信息在运行时会被擦除，这会导致 Gson 在反序列化泛型类型时无法确定具体的类型信息。
         * TypeToken 通过一个匿名类的方式保留了泛型的类型信息，使得 Gson 可以正确地反序列化复杂的泛型类型。
         *
         * new TypeToken<Page<TestProduct>>() {} 是一个匿名类，它保留了 Page<Product> 的类型信息。
         * getType() 方法返回 Type 对象，代表 Page<TestProduct> 的完整类型信息。
         * */
        Type responseType = new TypeToken<Resource<Page<TestProduct>>>(){}.getType();
        Resource<Page<TestProduct>> response = gson.fromJson(jsonResponse, responseType);
        data.setValue(Resource.success(response.getData()));

        return data;
    }
}

