object AndroidSdk {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.0"
    const val minSdkVersion = 19
}

object AndroidPlugins {

    object Version {
        const val kotlinVersion = "1.3.72"
        const val gradleVersion = "4.1.0"
    }

    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
    const val gradlePlugin = "com.android.tools.build:gradle:${Version.gradleVersion}"
    const val kotlinStdLibPlugin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlinVersion}"

}

object AndroidSupport {

    object Version {
        const val appcompatVersion = "1.2.0"
        const val coreKtxVersion = "1.3.2"
        const val constraintLayoutVersion = "2.0.4"
    }

    const val appcompatPlugin = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    const val coreKtxPlugin = "androidx.core:core-ktx:${Version.coreKtxVersion}"
    const val constraintLayoutPlugin = "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"

}

object UIPlugin {

    object Version {
        const val materialVersion = "1.1.0"
    }

    const val materialPlugin = "com.google.android.material:material:${Version.materialVersion}"

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
    }

    const val junitPlugin = "junit:junit:${Version.junitVersion}"
    const val testJunitVersion = "androidx.test.ext:junit:${Version.testJunitVersion}"
    const val testEspressoVersion = "androidx.test.espresso:espresso-core:${Version.testEspressoVersion}"
}

