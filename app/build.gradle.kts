import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.extraProperties

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.gradleup.tapmoc)
}

val pkg = "org.cufy.mmrpc.editor"

tapmoc {
    java(libs.versions.java.get().toInt())
    // kotlin(libs.versions.kotlin.get()) // see https://github.com/GradleUp/Tapmoc/issues/69
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }

    jvm("desktop")
    androidTarget()
    sourceSets {
        val jvmCommon by creating
        val commonMain by getting
        val desktopMain by getting
        val androidMain by getting
        jvmCommon.dependsOn(commonMain)
        desktopMain.dependsOn(jvmCommon)
        androidMain.dependsOn(jvmCommon)
    }
    sourceSets.commonMain.dependencies {
        // ##### Official Dependencies #####
        implementation(libs.kotlinx.serialization.json)
        implementation(libs.kotlinx.coroutines.core)
        implementation(libs.kotlinx.datetime)

        // ##### Builtin Dependencies #####
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.materialIconsExtended)
        implementation(compose.ui)
        implementation(compose.components.resources)
        implementation(compose.components.uiToolingPreview)

        // ##### Internal Dependencies #####

        implementation(libs.mmrpc.definition)

        implementation(libs.extkt.json)

        implementation(libs.lsafer.compose.simplenav)

        // ##### Community Dependencies #####

        implementation(libs.filekit.core)
        implementation(libs.filekit.dialogs)

        implementation(libs.mikepenz.markdownCompose)
        implementation(libs.mikepenz.markdownCompose.m3)

        implementation(libs.touchlab.kermit)

        // ##### ANDROID Dependencies #####

        implementation(libs.androidx.lifecycle.runtime.compose)
        implementation(libs.androidx.lifecycle.viewmodel)
        implementation(libs.androidx.lifecycle.viewmodel.compose)
    }
    sourceSets.named("desktopMain").dependencies {
        implementation(libs.kotlinx.coroutines.swing)
        implementation(compose.desktop.currentOs)

        implementation(libs.harawata.appdirs)
    }
    sourceSets.androidMain.dependencies {
        implementation(compose.preview)

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.activity.compose)
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = pkg
}

compose.desktop {
    application {
        mainClass = "$pkg.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "mmRPC Editor"
            packageVersion = rootProject.extraProperties["version_alt"].toString()
            vendor = "mmRPC"

            modules("jdk.security.auth")
        }
    }
}

android {
    namespace = pkg
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    lint {
        disable += "CoroutineCreationDuringComposition"
    }
    defaultConfig {
        applicationId = pkg
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
