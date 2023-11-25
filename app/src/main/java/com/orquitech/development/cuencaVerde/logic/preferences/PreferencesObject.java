package com.orquitech.development.cuencaVerde.logic.preferences;

public class PreferencesObject {
    private final String key;
    private final String fileName;
    private Object defaultValue;

    public PreferencesObject(String fileName, String key, Object defaultValue) {
        this.key = key;
        this.fileName = fileName;
        this.defaultValue = defaultValue;
    }

    protected String getKey() {
        return this.key;
    }

    protected Object getDefaultValue() {
        return this.defaultValue;
    }

    protected String getFileName() {
        return this.fileName;
    }

    public String toString() {
        return this.key;
    }
}
