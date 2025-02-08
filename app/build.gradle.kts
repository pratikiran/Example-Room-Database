plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.e4.explainroomdatabase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.e4.explainroomdatabase"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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

    // Room version
    val roomVersion = "2.6.1"

    // Room runtime
    implementation("androidx.room:room-runtime:$roomVersion")

    // Room annotation processor for Java projects
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
}