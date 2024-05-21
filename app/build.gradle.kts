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

}