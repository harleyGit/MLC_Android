package com.ganghuang.mlc2_android.Untils;

import android.content.Context;
import android.util.TypedValue;

public class HGUntil {

    /**
     * 根据手机的分辨率从 dp(相对大小) 的单位 转成为 px(像素)
     */
    public static int dpToPx(Context context, float dpValue) {
        // 获取屏幕密度
        final float scale = context.getResources().getDisplayMetrics().density;
        // 结果+0.5是为了int取整时更接近
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dpToPx2(Context context, float dpValue) {
        /**
         * TypedValue.applyDimension(): 这是一个Android SDK提供的用于将不同单位的尺寸值转换为像素值的方法。它接受三个参数：
         *
         * unit: 表示要转换的尺寸值的单位，例如TypedValue.COMPLEX_UNIT_DIP表示dp单位。
         * sizeInDp: 要转换的尺寸值，以dp为单位。
         * getDisplayMetrics(): 这是一个Resources对象的方法，返回当前设备的显示度量（DisplayMetrics）。它包含了关于屏幕的信息，如密度、宽度等。
         * */
        int sizeInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());

        return sizeInPx;
    }

    ;

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp(相对大小)
     */
    public static int pxToDp(Context context, float pxValue) {
        /**
         * 资料：https://blog.csdn.net/dongxianfei/article/details/124823147
         * 在Android设备中，屏幕的尺寸和密度各不相同。
         * 为了确保UI在不同设备上显示一致，我们需要使用屏幕密度来进行适配。
         * 例如，某个元素在高密度屏幕（如xxxhdpi）上可能需要更多的像素来显示同样的大小。
         * 因此，通过获取屏幕密度并进行相应的计算，可以使得UI元素在不同的设备上显示得更为一致
         *
         *
         * context: 通常是一个Activity或Application对象，通过它可以访问应用的资源和应用环境
         *
         * getResources(): 这是Context类的一个方法，它返回一个Resources对象。这个对象可以用来访问应用的资源，如字符串、图像、布局等。
         *
         * getDisplayMetrics(): 这是Resources类的一个方法，它返回一个DisplayMetrics对象。这个对象包含了关于屏幕的一些信息，例如屏幕尺寸、密度等。
         *
         * density: 这是DisplayMetrics对象的一个字段，表示屏幕的密度。它是一个浮点数，通常用来表示屏幕上每英寸的像素数量（dpi）。常见的密度值
         * */
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
