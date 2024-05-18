package com.ganghuang.mlc2_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityLifeCycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_life_cycle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("🍎", "onCreate 🍊");

        Button normalBtn = findViewById(R.id.start_normal_activity);
        Button dialogBtn = findViewById(R.id.start_dialog_activity);

        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLifeCycle.this, NormalActivity.class);
                startActivity(intent);
            }
        });


        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLifeCycle.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.d("🍎", "onStart 🍊");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("🍎", "onResume 🍊");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("🍎", "onPause 🍊");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("🍎", "onStop 🍊");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("🍎", "onDestroy 🍊");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("🍎", "onRestart 🍊");

    }
}