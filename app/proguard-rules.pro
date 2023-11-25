# LIBRARIES
###########

-dontwarn com.squareup.okhttp.**
-dontwarn org.apache.commons.**
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn android.app.Notification.**
-dontwarn com.almeros.android.**

-dontwarn com.viewpagerindicator.**
-dontwarn java.lang.invoke.**
-dontwarn com.google.firebase.**

-keep class org.apache.http.** { *; }
-keep class android.app.Notification.** { *; }
-keep class com.almeros.android.** { *; }
-keep class com.viewpagerindicator.** { *; }
-keep class com.google.firebase.** { *; }

# ****** B1G Digital *******

-keep class com.orquitech.development.cuencaVerde.data.api.model.** { *; }
-keep class com.orquitech.development.cuencaVerde.data.model.** { *; }

# ****** LeakCanary *******

-dontwarn com.squareup.haha.guava.**
-dontwarn com.squareup.haha.perflib.**
-dontwarn com.squareup.haha.trove.**
-dontwarn com.squareup.leakcanary.**
-keep class com.squareup.haha.** { *; }
-keep class com.squareup.leakcanary.** { *; }

# Marshmallow removed Notification.setLatestEventInfo()
-dontwarn android.app.Notification

# ****** Retrofit 2.x *******

-keepattributes Annotation,SourceFile,LineNumberTable
-keepclasseswithmembernames class * {native <methods>; }

-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

-keepclassmembers class * {
    native <methods>;
}
-dontwarn okio.**
-dontwarn javax.annotation.**

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**

# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.gson.internal.bind.TypeAdapters$* { *; }

-keep class com.google.gson.examples.android.model.** { *; }

# HockeyApp

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# ****** Rx *******

-dontwarn rx.**

-keep class rx.** { *; }

-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

# ****** Jsoup *******

-keep public class org.jsoup.** {
public *;
}
