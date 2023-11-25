package com.orquitech.development.cuencaVerde.logic.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.SerializationManager;

public class AppPreferencesManager implements PreferencesManager {

    private App app;
    private SerializationManager serializationManager;
    private GenericPreferencesFactory preferencesFactory;

    public AppPreferencesManager(App app, SerializationManager serializationManager, GenericPreferencesFactory preferencesFactory) {
        this.app = app;
        this.serializationManager = serializationManager;
        this.preferencesFactory = preferencesFactory;
    }

    private SharedPreferences getPreferencesFile(String groupPreferenceName) {
        return this.app.getSharedPreferences(groupPreferenceName, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor(String groupPreferenceName) {
        SharedPreferences preferences = this.getPreferencesFile(groupPreferenceName);
        return preferences.edit();
    }

    @Override
    public Object get(int key, Class classType) {
        String objectString = getString(key);
        return getSerializationManager().fromJson(objectString, classType);
    }

    public String getString(int preferencesObjectName) {
        PreferencesObject preferencesObject = preferencesFactory.getPreferencesObject(preferencesObjectName);
        if (preferencesObject == null) {
            return "";
        }
        SharedPreferences sharedPref = app.getSharedPreferences(preferencesObject.getFileName(), Context.MODE_PRIVATE);
        return sharedPref.getString(preferencesObject.getKey(), (String) preferencesObject.getDefaultValue());
    }

    public void set(int key, Object object) {
        String value = getSerializationManager().toJson(object);
        PreferencesObject preferencesObject = preferencesFactory.getPreferencesObject(key);
        SharedPreferences.Editor editor = this.getEditor(preferencesObject.getFileName());
        editor.putString(preferencesObject.getKey(), value);
        editor.apply();
    }

    @Override
    public void remove(int key) {
        PreferencesObject preferencesObject = preferencesFactory.getPreferencesObject(key);
        if (preferencesObject != null) {
            SharedPreferences.Editor editor = this.getEditor(preferencesObject.getFileName());
            editor.remove(preferencesObject.getKey());
            editor.apply();
        }
    }

    private void removeBlocking(int key) {
        PreferencesObject preferencesObject = preferencesFactory.getPreferencesObject(key);
        if (preferencesObject != null) {
            SharedPreferences.Editor editor = this.getEditor(preferencesObject.getFileName());
            editor.remove(preferencesObject.getKey());
            editor.commit();
        }
    }

    @Override
    public void clearUser() {
        removeBlocking(AppPreferencesObjectFactory.AUTH);
        removeBlocking(AppPreferencesObjectFactory.PQRS);
        removeBlocking(AppPreferencesObjectFactory.ONE_SIGNAL_ID);
    }

    protected SerializationManager getSerializationManager() {
        return serializationManager;
    }

    public void resetGroupPreferences(int groupPreferenceName) {
        PreferencesObject preferencesObject = preferencesFactory.getPreferencesObject(groupPreferenceName);
        SharedPreferences.Editor editor = this.getEditor(preferencesObject.getFileName());
        editor.clear().apply();
    }
}
