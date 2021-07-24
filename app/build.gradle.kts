plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.jabezmagomere.posts"
        minSdkVersion(16)
        targetSdkVersion(30)
        versionCode = 1
        versionName("1.0")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
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

    (kotlinOptions).apply {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(Dependencies.kotlin)
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.constraintLayout)

    implementation(Dependencies.material)

    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.LifeCycle.runtime)

    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.compiler)
    implementation(Dependencies.Room.ktx)

    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.gson)
    implementation(Dependencies.Network.interceptor)
    implementation(Dependencies.Network.okHttp)

    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.viewModel)

    implementation(Dependencies.timber)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.Room.testing)
    androidTestImplementation(TestDependencies.AndroidX.junit)
    androidTestImplementation(TestDependencies.AndroidX.espresso)
}