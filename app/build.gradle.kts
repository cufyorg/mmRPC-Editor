
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.android.application)
}

kotlin {
    jvm("desktop")

    // TODO js support
    if (false) js {
        moduleName = "org.cufy.mmrpc.editor"
        browser {
            commonWebpackConfig {
                outputFileName = "app.js"

                val devOpen: String? by project
                val devPort: String? by project

                devServer?.open = devOpen?.toBooleanStrictOrNull() ?: true
                devServer?.port = devPort?.toIntOrNull()
            }
        }
        binaries.executable()
        useEsModules()
    }

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            // ##### Official Dependencies #####
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)

            implementation(libs.okio)

            // ##### Builtin Dependencies #####
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.material3AdaptiveNavigationSuite)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // ##### Internal Dependencies #####

            implementation(libs.mmrpc.runtime)
            implementation(libs.mmrpc.definition)

            implementation(libs.extkt.json)

            implementation(libs.lsafer.sundry.compose)
            implementation(libs.lsafer.sundry.compose.adaptive)
            implementation(libs.lsafer.sundry.storage)

            // ##### Community Dependencies #####

            implementation(libs.vinceglb.filekit.core)
            implementation(libs.vinceglb.filekit.compose)

            implementation(libs.mikepenz.markdownCompose)
            implementation(libs.mikepenz.markdownCompose.m3)

            implementation(libs.touchlab.kermit)
            implementation(libs.charleskorn.kaml)

            // ##### ANDROID Dependencies #####

            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodel.compose)

            implementation(libs.material3.adaptive)
            implementation(libs.material3.adaptive.layout)
            implementation(libs.material3.adaptive.navigation)
        }

        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            implementation(compose.desktop.currentOs)

            implementation(libs.harawata.appdirs)
        }

        androidMain.dependencies {
            implementation(compose.preview)

            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.constraintlayout)
            implementation(libs.androidx.activity.compose)
        }

        if (false) jsMain.dependencies {
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "org.cufy.mmrpc.editor"
}

compose.desktop {
    application {
        mainClass = "org.cufy.mmrpc.editor.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.cufy.mmrpc.editor"
            packageVersion = "1.0.0"
        }
    }
}

android {
    namespace = "org.cufy.mmrpc.editor"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    lint {
        disable += "CoroutineCreationDuringComposition"
    }
    defaultConfig {
        applicationId = "org.cufy.mmrpc.editor"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
