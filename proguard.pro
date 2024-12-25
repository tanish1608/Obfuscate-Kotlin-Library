-verbose

# Keep annotations and metadata attributes for Kotlin
-keepattributes *Annotation*
-keepattributes Kotlin*

# Keep Kotlin's standard library and coroutines
-keep class kotlin.** { *; }
-keep class kotlinx.coroutines.** { *; }
-keep class kotlinx.serialization.** { *; }



# Preserve the Calculator and AdvancedCalculator classes and their public methods
-keep public class com.example.Calculator {
    public <methods>;
}
# Preserve enums (required for reflection and some Kotlin features)
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# Keep Serializable class names for deserialization
-keepnames class * implements java.io.Serializable

# Remove unused code
-dontshrink
# Enable optimizations for better performance
-optimizationpasses 5
-allowaccessmodification

# Obfuscate all other classes and repack into a single package
-repackageclasses ''

# Minify resource files
-adaptresourcefilenames
-adaptresourcefilecontents

# Optional for debugging during development
-keepattributes LineNumberTable, SourceFile
-printusage usage.txt
-printmapping mapping.txt