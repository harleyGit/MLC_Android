plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.ganghuang.mlc2_android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ganghuang.mlc2_android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //implementation("com.android.support:appcompat-v7:24.2.1")
    //implementation("com.android.support:recyclerview-v7:24.2.1")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    //testImplementation("junit:junit:4.12")

    //fastjson json解析库：https://blog.csdn.net/qq_20451879/article/details/72477211
    implementation ("com.alibaba:fastjson:1.1.72.android")
    //网络请求库
    implementation("com.squareup.okhttp3:okhttp:3.4.1")
    //Gson库：https://central.sonatype.com/artifact/com.google.code.gson/gson/overview
    implementation("com.google.code.gson:gson:2.11.0")

    //Glide加载和显示图片的三方库
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    /**
     * annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0' 的主要作用是处理 Glide 的注解，自动生成辅助代码（如 GlideApp 类），从而简化代码编写并提供更强大的功能和优化。
     * 这是 Glide 提供的一种标准实践，用于增强和定制图片加载和处理的功能
     * */
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

}