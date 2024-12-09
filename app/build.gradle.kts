plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)

}


android {

    namespace = "cl.ipvg.puebadb"
    compileSdk = 34


    defaultConfig {

        applicationId = "cl.ipvg.puebadb"
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

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {


    configurations.all {
        resolutionStrategy {
            force ("androidx.annotation:annotation:1.5.0")  // Usar esta versión para todas las dependencias
        }
    }

    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation ("com.google.firebase:firebase-database:20.0.5")
      // Firebase Authentication, si la necesitas
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    // Para Maps si lo necesitas

    // para el Recycler view y las imagenes
    implementation ("androidx.recyclerview:recyclerview:1.3.0")

//

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}