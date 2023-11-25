package com.orquitech.development.cuencaVerde.logic;

import android.util.Log;

public class LogManager {

    private boolean shouldLog;

    public LogManager(boolean shouldLog) {
        this.shouldLog = shouldLog;
    }

    public void log(String tag, String message) {
        if (shouldLog) {
            Log.d(tag, message);
        }
    }

    public void log(String tag, Exception exception) {
        if (shouldLog) {
            Log.e(tag, exception.getMessage());
        }
    }

    public void log(String tag, Throwable exception) {
        if(shouldLog) {
            Log.e(tag, exception.getMessage());
        }
    }
}
