package com.orquitech.development.cuencaVerde.presentation.utils;

import android.content.Context;

import java.text.Normalizer;
import java.util.Locale;

public final class TextUtil {

    public TextUtil() {
    }

    public static String getString(Context context, int stringResource) {
        return context.getString(stringResource);
    }

    public static String getString(Context context, int stringResource, Object... parameters) {
        return context.getString(stringResource, parameters);
    }

    public static String getStringFromDouble(Context context, int stringResource, double value) {
        String stringValue = String.format(Locale.US, "%.2f", value);
        return context.getString(stringResource, stringValue);
    }

    public static String stripAccents(String word) {
        word = Normalizer.normalize(word, Normalizer.Form.NFD);
        word = word.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return word;
    }
}
