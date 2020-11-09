buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath (AndroidPlugins.gradlePlugin)
        classpath (AndroidPlugins.kotlinPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

tasks.register("clean").configure {
    delete("build")
}