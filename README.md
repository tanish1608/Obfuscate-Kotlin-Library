# Step-by-Step Guide to Obfuscate Kotlin Files Using ProGuard

This guide explains how to obfuscate a Kotlin project using ProGuard while retaining specific class and method names.

---

## Prerequisites

1. **Kotlin Project**: Ensure you have a Kotlin project set up with Gradle.
2. **ProGuard Dependency**: Add ProGuard to your Gradle build file.

---

## Step 1: Add ProGuard to Your Project

### Update `build.gradle`

```gradle
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'com.guardsquare:proguard-gradle:7.6.1'
    }
}

plugins {
    id 'org.jetbrains.kotlin.jvm' version '1.9.0'
    id 'application'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.jetbrains.kotlin:kotlin-stdlib'
    testImplementation 'org.jetbrains.kotlin:kotlin-test'
}
```
## Step 2: Configure ProGuard Task

### Add a ProGuard task to your build.gradle:

```
import proguard.gradle.ProGuardTask

tasks.register('proguard', ProGuardTask) {
    configuration file('proguard.pro')

    injars(tasks.named('jar', Jar).flatMap { it.archiveFile })

    libraryjars "${System.getProperty('java.home')}/jmods/java.base.jmod"
    libraryjars sourceSets.main.compileClasspath

    outjars(layout.buildDirectory.file("libs/app-minified.jar"))

    verbose
}
```

## Step 3: Create proguard.pro

### Create a file named proguard.pro in your project root. Add rules to control obfuscation.

Example ProGuard Rules
```
# Verbose logging
-verbose

# Preserve annotations and metadata for Kotlin compatibility
-keepattributes *Annotation*
-keepattributes Kotlin*

# Preserve specific classes and methods
-keep public class com.example.Calculator {
    public <methods>;
}

# Obfuscate all other classes and methods
-repackageclasses ''

# Keep Serializable class names
-keepnames class * implements java.io.Serializable

# Preserve enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Enable optimizations
-optimizationpasses 5
-allowaccessmodification

# Minify resource files
-adaptresourcefilenames
-adaptresourcefilecontents

# Debugging (optional)
-keepattributes LineNumberTable, SourceFile
-printmapping mapping.txt
-printusage usage.txt
```

## Step 4: Build the Project
```

# Run the ProGuard task:
./gradlew proguard
# The obfuscated JAR file will be generated in the build/libs directory.
```

## Step 5: Verify Obfuscation
1.	Open the mapping.txt file generated in the project root.
Verify that:
- he Calculator class and its public methods are preserved.
- other classes and methods are obfuscated.

Optional: Preserve Additional Classes or Methods

If you need to keep more classes or methods intact, update proguard.pro with additional -keep rules. For example:

```
-keep public class com.example.AdvancedCalculator {
    public <methods>;
}
```




