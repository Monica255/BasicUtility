plugins {
    alias(libs.plugins.android.library)
    `maven-publish`
}

android {
    namespace = "com.basicutil"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    publishing { // Add this
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Lifecycle / Activity
    implementation(libs.lifecycle.runtime)
    implementation(libs.activity.compose)
}

publishing {
    publications {
        create("release", MavenPublication::class) {
            groupId = "com.github.monica255"
            artifactId = "basicutil"
            version = "0.0.1"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}