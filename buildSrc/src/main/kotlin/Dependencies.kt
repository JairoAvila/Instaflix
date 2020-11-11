object AndroidSdk {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.0"
    const val minSdkVersion = 19
}

object AndroidPlugins {

    object Version {
        const val kotlinVersion = "1.3.72"
        const val gradleVersion = "4.1.0"
        const val googleServicesVersion = "4.3.4"
    }

    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
    const val gradlePlugin = "com.android.tools.build:gradle:${Version.gradleVersion}"
    const val kotlinStdLibPlugin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlinVersion}"
    const val googleServicesPlugin = "com.google.gms:google-services:${Version.googleServicesVersion}"

}

object AndroidSupport {

    object Version {
        const val appcompatVersion = "1.2.0"
        const val coreKtxVersion = "1.3.2"
    }

    const val appcompatPlugin = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    const val coreKtxPlugin = "androidx.core:core-ktx:${Version.coreKtxVersion}"

}

object UIPlugin {

    object Version {
        const val materialVersion = "1.1.0"
        const val recyclerviewVersion = "1.1.0"
        const val cardviewVersion = "1.0.0"
        const val constraintLayoutVersion = "2.0.4"
    }

    const val constraintLayoutPlugin = "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
    const val materialPlugin = "com.google.android.material:material:${Version.materialVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerviewVersion}"
    const val cardView = "androidx.cardview:cardview:${Version.cardviewVersion}"

}

object ArchComponentsLibraries {

    object Version {
        const val lifecycle_version = "2.2.0"
        const val navigation_version = "2.3.1"
        const val paging_version = "2.1.2"
        const val room_version = "2.2.5"
    }

    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle_version}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle_version}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle_version}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation_version}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Version.navigation_version}"
    const val paging =  "androidx.paging:paging-runtime:${Version.paging_version}"
    const val roomRuntime =  "androidx.room:room-ktx:${Version.room_version}"
    const val roomCompiler =  "androidx.room:room-compiler:${Version.room_version}"

}

object NetworkLibraries {

    private object Version {
        const val retrofit_version = "2.9.0"
        const val okhttp_version = "4.7.2"
    }

    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit_version}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp_version}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp_version}"
    const val gsonConverter ="com.squareup.retrofit2:converter-gson:${Version.retrofit_version}"
}

object FirebaseLibraries {

    private object Version {
        const val bom_version = "26.0.0"
        const val crashlytics_gradle_version = "2.3.0"
    }

    const val bom = "com.google.firebase:firebase-bom:${Version.bom_version}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics"
    const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${Version.crashlytics_gradle_version}"
    const val analytics = "com.google.firebase:firebase-analytics"

}

object DILibraries {

    private object Version {
        const val koin_version = "2.1.6"
    }

    const val koin = "org.koin:koin-android:${Version.koin_version}"
    const val koinScope = "org.koin:koin-androidx-scope:${Version.koin_version}"
    const val koinViewmodel = "org.koin:koin-androidx-viewmodel:${Version.koin_version}"
    const val koinFragment = "org.koin:koin-androidx-fragment:${Version.koin_version}"

}

object UtilsLibraries {

    private object Version {
        const val coil_version = "0.11.0"
        const val leak_canary = "2.3"
    }

    const val coil = "io.coil-kt:coil:${Version.coil_version}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.leak_canary}"
}

object AnalysisCode {
    object AnalysisCodeVersions {
        const val ktlint_gradle_version = "8.2.0"
        const val ktlint_version = "0.36.0"
    }

    const val ktlinGradle = "org.jlleitschuh.gradle.ktlint"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
}

object Testing {

    object Version {
        const val junitVersion = "4.13.1"
        const val testJunitVersion = "1.1.2"
        const val testEspressoVersion = "3.3.0"
        const val core_testing_version = "2.1.0"
        const val coroutines_test_version = "1.3.6"
        const val expekt_version = "0.5.0"
        const val mockito_version = "3.3.3"
    }

    const val junit = "junit:junit:${Version.junitVersion}"
    const val testJunit = "androidx.test.ext:junit:${Version.testJunitVersion}"
    const val testEspresso = "androidx.test.espresso:espresso-core:${Version.testEspressoVersion}"
    const val coreTesting = "androidx.arch.core:core-testing:${Version.core_testing_version}"
    const val coroutinesTesting = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines_test_version}"
    const val expekt = "com.winterbe:expekt:${Version.expekt_version}"
    const val mockitoCore = "org.mockito:mockito-core:${Version.mockito_version}"
    const val mockitoInline = "org.mockito:mockito-inline:${Version.mockito_version}"
}

