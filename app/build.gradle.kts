plugins {
    alias(libs.plugins.androidApplication)
}

android {//这是一个闭包，用于配置 Android 项目的构建设置
    namespace = "com.ganghuang.mlc2_android"    //设置应用程序的命名空间
    compileSdk = 34 //设置编译时使用的 SDK 版本为

    defaultConfig {
        applicationId = "com.ganghuang.mlc2_android"
        minSdk = 24 //可运行应用的最低 Android 平台版本，由平台的 API 级别标识符指定
        targetSdk = 34  //指定运行应用的目标 API 级别。在某些情况下，这允许应用使用针对目标 API 级别定义的清单元素或行为，而不是仅限于使用那些针对最低 API 级别定义的清单元素或行为
        versionCode = 1 //值表示当前 APK 包含应用的第二个版本
        versionName = "1.0" //字符串则指定向用户显示的应用版本为版本 1.0

        //signingConfig(signingConfigs.getByName("config")): 设置应用程序的签名配置为名为 "config" 的签名配置
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"   //设置测试运行器为 AndroidJUnitRunner
    }

    /*
    signingConfigs {//这是一个闭包，用于配置应用程序的签名信息
        //若是在gradle.properties(Project Properties)中将keystore文件的各种信息以键值对的形式进行了配置，
        //然后我们在build.gradle中去读取这些数据就可以了
        create("config") {//创建一个名为 "config" 的签名配置
            //设置签名文件的路径
            storeFile = file("C:/Users/Administrator/Documents/guolin.jks") //file(KEY_PATH) 在gradle.properties(Project Properties)配置了

            //设置签名文件的密码
            storePassword = "1234567"   //KEY_PASS 在gradle.properties(Project Properties)配置了

            //设置密钥别名
            keyAlias = "guolindev"  //ALIAS_NAME 在gradle.properties(Project Properties)配置了

            //设置密钥密码
            keyPassword = "1234567" //ALIAS_PASS
        }
    }
     */

    buildTypes {//这是一个闭包，用于配置不同的构建类型
        release {//获取名为 "release" 的构建类型
            isMinifyEnabled = false //设置是否启用代码混淆为 false
            proguardFiles(  //设置混淆规则文件
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {//这是一个闭包，用于配置编译选项
        sourceCompatibility = JavaVersion.VERSION_1_8 //设置源代码兼容性为 Java 1.8
        targetCompatibility = JavaVersion.VERSION_1_8 //设置目标代码兼容性为 Java 1.8
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


    implementation("org.greenrobot:eventbus:3.2.0")//EventBus 库是一个发布/订阅事件总线库，用于在不同组件之间传递消息，简化组件之间的通信

    implementation("com.google.android.gms:play-services-location:21.0.1")//添加Google Play定位服务依赖项

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