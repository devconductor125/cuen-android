package com.orquitech.development.cuencaVerde.data.utils;

public class OsUtils {

    public static boolean isAtLeastOreo() {
        return android.os.Build.VERSION.SDK_INT >= 26;
    }
}
