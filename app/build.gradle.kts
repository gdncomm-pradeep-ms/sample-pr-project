plugins {
  id("com.android.application")
  jacoco
}

android {
  namespace = "com.example.samplepr"
  compileSdk = 37

  defaultConfig {
    applicationId = "com.example.samplepr"
    minSdk = 26
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  testOptions {
    unitTests.isReturnDefaultValues = true
  }
}

tasks.withType<Test>().configureEach {
  extensions.getByType(org.gradle.testing.jacoco.plugins.JacocoTaskExtension::class.java).apply {
    isIncludeNoLocationClasses = true
    excludes = listOf("jdk.internal.*")
  }
}

tasks.register<JacocoReport>("jacocoTestReport") {
  group = "verification"
  dependsOn("testDebugUnitTest")

  reports {
    xml.required.set(true)
    html.required.set(true)
  }

  val fileFilter = listOf(
    "**/R.class", "**/R\$*.class", "**/BuildConfig.*", "**/Manifest*.*"
  )

  val buildDirectory = layout.buildDirectory.get().asFile
  classDirectories.setFrom(
    files(
      fileTree("$buildDirectory/intermediates/javac/debug/classes") { exclude(fileFilter) },
      fileTree("$buildDirectory/intermediates/built_in_kotlinc/debug/compileDebugKotlin/classes") { exclude(fileFilter) }
    )
  )
  sourceDirectories.setFrom(files("$projectDir/src/main/java"))
  executionData.setFrom(
    fileTree(buildDirectory) { include("jacoco/testDebugUnitTest.exec") }
  )
}

dependencies {
  implementation("androidx.core:core-ktx:1.13.1")
  testImplementation("junit:junit:4.13.2")
}
