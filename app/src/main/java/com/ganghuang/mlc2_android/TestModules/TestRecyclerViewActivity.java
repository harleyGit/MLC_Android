package com.ganghuang.mlc2_android.TestModules;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ganghuang.mlc2_android.R;
import com.ganghuang.mlc2_android.TestModuleAdapter;
import com.ganghuang.mlc2_android.TestModules.JsonParser;
import com.ganghuang.mlc2_android.TestModules.TestFruitAdapter;

import java.util.List;

public class TestRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_recycler_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.testJsonParser();
    }


    private void testJsonParser(){
        List<TestFruitAdapter.TestModuleFunctionModel> functionList = JsonParser.parseJson(this, "ModuleFunction.json");
        if (functionList != null) {
            for (TestFruitAdapter.TestModuleFunctionModel function : functionList) {
                Log.d("🍎JSON", "ID: " + function.getFunctionId() +
                        ", Name: " + function.getFunctionName() +
                        ", Pic: " + function.getFunctionPic());
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        TestModuleAdapter adapter = new TestModuleAdapter(this,functionList);
        recyclerView.setAdapter(adapter);
    }
}








