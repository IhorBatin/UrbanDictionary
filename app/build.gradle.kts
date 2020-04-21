plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Versions.compile)
    defaultConfig {
        applicationId = "com.example.urbandictionarynike"
        minSdkVersion(Versions.min)
        targetSdkVersion(Versions.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Kotlin.coreKtx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidArchitecture.navigationKtx)
    implementation(Dependencies.AndroidArchitecture.navigationUiKtx)
    implementation(Dependencies.Parsing.moshi)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitMoshi)
    implementation(Dependencies.Network.retrofitCoroutines)
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.okhttpInterceptor)

    implementation(Dependencies.MultiThreading.coroutinesCore)
    implementation(Dependencies.MultiThreading.coroutinesAndroid)

    implementation(Dependencies.LocaleDb.room)
    implementation(Dependencies.LocaleDb.roomKotlin)

    kapt(Dependencies.Parsing.moshiCodeGen)
    kapt(Dependencies.LocaleDb.roomCompiler)

    testImplementation(Dependencies.Testing.junit4)
    testImplementation(Dependencies.Testing.coroutines)
    testImplementation(Dependencies.Testing.testExt)
    testImplementation(Dependencies.Testing.espresso)
    testImplementation(Dependencies.Testing.mockk)
    testImplementation(Dependencies.Testing.core)
    testImplementation(Dependencies.Testing.room)

    androidTestImplementation(Dependencies.Testing.coroutines)
}
