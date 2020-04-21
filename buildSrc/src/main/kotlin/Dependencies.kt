object Versions {
    // Kotlin
    const val kotlinVersion = "1.3.61"

    // Build
    const val min = 21
    const val compile = 29
    const val target = compile

    // AndroidX
    const val appcompat = "1.1.0"
    const val coreKtx = "1.2.0"
    const val constraintLayout = "1.1.3"
    const val navigation = "2.2.1"
    const val cardView = "1.0.0"

    // Moshi
    const val moshi = "1.9.2"
    const val moshiRetrofit = "2.6.1"

    // Plugins
    const val safeArgs = "2.2.1"
    const val androidGradle = "3.6.2"

    // Lifecycle
    const val lifecycle = "2.2.0"

    // Network
    const val retrofit = "2.7.2"
    const val retrofitCoroutines = "0.9.2"
    const val okhttp = "4.3.1"

    // MultiThreading
    const val coroutine = "1.3.0"

    // LocaleDb
    const val room = "2.2.5"

    // Tests
    const val testExt = "1.1.1"
    const val espresso = "3.2.0"
    const val junit4 = "4.1.3"
    const val testRunner = "3.2.0"
    const val arch = "2.1.0"
    const val mockk = "1.9.3"
}

object GradlePlugins {
    const val android = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
}

object Dependencies {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.appcompat}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object AndroidArchitecture {
        const val navigationKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Parsing {
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.moshiRetrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object MultiThreading {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    }

    object LocaleDb {
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKotlin = "androidx.room:room-ktx:${Versions.room}"
    }

    object Testing {
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val junit4 = "junit:junit:${Versions.junit4}"
        const val core = "androidx.arch.core:core-testing:${Versions.arch}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
        const val room = "androidx.room:room-testing:${Versions.room}"
    }
}