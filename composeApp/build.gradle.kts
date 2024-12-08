import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        commonMain.dependencies {
            //Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.material3AdaptiveNavigationSuite)
            implementation(libs.compose.imageloader)
            implementation(libs.compose.material3.window.size)
            implementation(libs.compose.material3.adaptive)
            implementation(libs.navigation.compose)
            //Voyager
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tab.navigator)
            //Datetime
            implementation(libs.kotlinx.datetime)
            //Logger
            implementation(libs.napier)
            //Constraint Layout
            implementation(libs.constraintlayout.compos)
            //Coroutines
            implementation(libs.kotlinx.coroutines.core)
            //Logger
            implementation(libs.napier)
            //JSON
            api(libs.kotlinx.serialization.json)
            // DI
            api(libs.koin.core)
            //Datetime
            implementation(libs.kotlinx.datetime)
        }

        androidMain.dependencies {
            //Compose
            implementation(libs.activity.compose)
            implementation(compose.uiTooling)
            //Coroutines
            implementation(libs.kotlinx.coroutines.android)
            //DI
            implementation(libs.koin.android)
        }
    }
}

android {
    namespace = "com.museady.cmp.ecommerce"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.museady.cmp.ecommerce"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
    dependencies {
        coreLibraryDesugaring(libs.desugar.jdk.libs)
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
