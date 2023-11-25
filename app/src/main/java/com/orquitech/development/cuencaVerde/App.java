package com.orquitech.development.cuencaVerde;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;

import com.orquitech.development.cuencaVerde.dependencyInjection.component.AppComponent;

public interface App {

    AppComponent getAppComponent();

    Context getApplicationContext();

    SharedPreferences getSharedPreferences(String groupPreferenceName, int modePrivate);

    ContentResolver getContentResolverObject();

    void notifyUser(String title, String message);

    void getObjectsInBackground(boolean connected);

    void initOneSignal();

    boolean isNetworkConnected(Context context);
}
