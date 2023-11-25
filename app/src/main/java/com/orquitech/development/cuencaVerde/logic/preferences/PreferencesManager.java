package com.orquitech.development.cuencaVerde.logic.preferences;

public interface PreferencesManager {

    void set(int key, Object object);

    Object get(int key, Class classType);

    void remove(int key);

    void clearUser();
}
