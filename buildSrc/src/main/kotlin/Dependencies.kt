object Dependencies {

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.androidxCore}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object Coroutines {
        const val core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object LifeCycle {
        const val runtime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val interceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    }


    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val viewModel = "io.insert-koin:koin-android-viewmodel:${Versions.koin}"
    }

    const val material = "com.google.android.material:material:${Versions.material}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestDependencies {

    object AndroidX {
        const val junit = "androidx.test.ext:junit:${Versions.androidxJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    }

    object Room {
        const val testing = "androidx.room:room-testing:${Versions.room}"
    }

    const val junit = "junit:junit:${Versions.junit}"
}