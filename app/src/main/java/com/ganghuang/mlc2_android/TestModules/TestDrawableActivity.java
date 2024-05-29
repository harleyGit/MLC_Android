package com.ganghuang.mlc2_android.TestModules;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganghuang.mlc2_android.R;
import com.ganghuang.mlc2_android.Untils.HGUntil;

public class TestDrawableActivity extends AppCompatActivity {
    public static void actionJumpToTestDrawableActivity(Context context) {//跳转到TestDrawableActivity
        Intent intent = new Intent(context, TestDrawableActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_drawable);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        this.testCircleOfTextView();
        this.testSetPicAndTxtPosition();

       // this.testPicTxtOfPosition();
    }


    private  void testPicTxtOfPosition(){//设置汉库克图片

        // 获取 RelativeLayout, ImageView 和 TextView
        RelativeLayout relativeLayout = findViewById(R.id.test_drawable_activity_relativeLayout);
        ImageView imageView = findViewById(R.id.test_drawable_activity_imageView00);
        TextView textView = findViewById(R.id.test_drawable_activity_textView00);

        // 获取 TextView 的布局参数
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();

        // 设置 TextView 的左边距为 20dp
        int marginInDp = 16;
        float scale = getResources().getDisplayMetrics().density;
        int marginInPx = (int) (marginInDp * scale + 0.5f);
        params.setMargins(marginInPx, 0, 0, 0);

        // 将 TextView 设置为位于 ImageView 的右侧，并与 ImageView 的顶部对齐
        params.addRule(RelativeLayout.ALIGN_TOP, R.id.test_drawable_activity_imageView00);
        params.addRule(RelativeLayout.RIGHT_OF, R.id.test_drawable_activity_imageView00);

        // 应用布局参数
        textView.setLayoutParams(params);
    }


    private void testSetPicAndTxtPosition() {//设置艾斯图片和文字的位置
        // 获取左边图片和TextView
        ImageView leftImage = findViewById(R.id.test_drawable_activity_leftImage);
        TextView textView = findViewById(R.id.test_drawable_activity_txtView01);

        // 创建布局参数对象，并设置规则
        ConstraintLayout.LayoutParams params = (/**/ConstraintLayout.LayoutParams) textView.getLayoutParams();
        params.startToEnd = R.id.test_drawable_activity_leftImage; // 设置左边界对齐左边图片
        params.topToTop = R.id.test_drawable_activity_leftImage; // 设置顶部对齐左边图片
        params.setMargins(20, 0, 0, 0); // 设置左边距为20dp

        // 应用布局参数
        textView.setLayoutParams(params);
    }

    private void testCircleOfTextView() {//测试圆角和左边图片
        TextView textView = findViewById(R.id.test_drawable_activity_txtView00);


        /**
         * 设置TextView的圆角没有效果的主要原因可能是因为在设置CornerRadius的时候，
         * TextView还没有完成测量和布局，因此textView.getHeight()返回的是0。
         * */
        //方法一
        textView.post(new Runnable() {
            @Override
            public void run() {
                //设置圆角
                GradientDrawable gradientDrawable = new GradientDrawable();

                //int radius = HGUntil.pxToDp(TestDrawableActivity.this,textView.getHeight()/2);
                //上述的方法无法正确设置其半径，这个注意
                int radius = textView.getHeight() / 2;

                //将 cornerRadius 设置为 TextView 高度或宽度的二分之一
                gradientDrawable.setCornerRadius(radius);
                //setColor() 方法设置纯色填充，或使用 setGradient() 方法设置渐变填充。
                gradientDrawable.setColor(Color.YELLOW);

                textView.setBackground(gradientDrawable);
            }
        });

        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 移除监听器，防止重复调用
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //设置左边图片
                int sizeInDp = 20;
                int paddingInDp = 10; // 图片与文字间距为10dp


                int sizeInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDp, getResources().getDisplayMetrics());
                int paddingInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, paddingInDp, getResources().getDisplayMetrics());

                /**
                 * 这一行代码从应用的资源中获取指定的drawable对象。
                 *
                 * ContextCompat.getDrawable() 方法用于获取drawable，第一个参数是上下文（Context），通常是 MainActivity.this，
                 * 第二个参数是资源的ID，R.drawable.your_drawable代表了要获取的drawable资源。
                 * */
                Drawable drawable = ContextCompat.getDrawable(TestDrawableActivity.this, R.drawable.img_1);

                if (drawable != null) {
                    /**
                     * setBounds() 方法用于设置drawable的边界。这里的参数表示左、上、右、下四个边界的位置。
                     * sizeInPx 是我们之前计算得到的图片大小，这里设置的边界使得图片的宽度和高度都为20dp，确保了图片大小的一致性。
                     * */
                    drawable.setBounds(0, 0, sizeInPx, sizeInPx);
                    /**
                     * setCompoundDrawables() 方法用于设置TextView的左、上、右、下四个方向的drawable对象。
                     * 在这里，我们将drawable设置在左侧，因此将drawable对象传递给第一个参数。
                     * 其他三个参数分别代表上、右、下方向的drawable，这里设置为null表示不设置
                     * */
                    textView.setCompoundDrawables(drawable, null, null, null);
                    //设置文字与图片之间的间距为10dp
                    textView.setCompoundDrawablePadding(paddingInPx);
                }
            }
        });

    }
}

