buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:9.3.1")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.10")
    classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:7.5.0.8588")
  }
}

plugins {
  id("org.sonarqube") version "7.5.0.8588"
}

sonar {
  properties {
    property("sonar.projectKey", "sample-pr-project-test")
    property("sonar.projectName", "sample-pr-project-test")
    property("sonar.sourceEncoding", "UTF-8")
    property("sonar.java.coveragePlugin", "jacoco")
    property(
      "sonar.coverage.jacoco.xmlReportPaths",
      "${project.projectDir}/app/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml"
    )
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.layout.buildDirectory)
}
