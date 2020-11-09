plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)
    buildToolsVersion(AndroidSdk.buildToolsVersion)

    defaultConfig {
        applicationId = "com.instaleap.instaflix"
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.compileSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Support Libraries
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(AndroidPlugins.kotlinStdLibPlugin)
    implementation(AndroidSupport.coreKtxPlugin)
    implementation(AndroidSupport.appcompatPlugin)

    // UI Libraries
    implementation(UIPlugin.constraintLayoutPlugin)
    implementation(UIPlugin.recyclerView)
    implementation(UIPlugin.cardView)
    implementation(UIPlugin.materialPlugin)

    // Architecture Components Libraries
    implementation(ArchComponentsLibraries.viewmodel)
    implementation(ArchComponentsLibraries.lifecycleExtensions)
    implementation(ArchComponentsLibraries.livedata)
    implementation(ArchComponentsLibraries.navigationFragment)
    implementation(ArchComponentsLibraries.navigationUI)
    implementation(ArchComponentsLibraries.paging)
    implementation(ArchComponentsLibraries.roomRuntime)
    kapt(ArchComponentsLibraries.roomCompiler)

    // Dependency Injection Library
    implementation(DILibraries.koinViewmodel)
    implementation(DILibraries.koinFragment)
    implementation(DILibraries.koin)
    implementation(DILibraries.koinScope)

    // Utils Libraries
    implementation(UtilsLibraries.coil)
    implementation(UtilsLibraries.leakCanary)

    // Testing Libraries
    testImplementation(Testing.junitPlugin)
    androidTestImplementation(Testing.testJunitVersion)
    androidTestImplementation(Testing.testEspressoVersion)
}