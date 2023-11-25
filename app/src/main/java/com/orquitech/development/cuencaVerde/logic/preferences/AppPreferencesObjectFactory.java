package com.orquitech.development.cuencaVerde.logic.preferences;

public class AppPreferencesObjectFactory implements GenericPreferencesFactory {

    private static final String APP_PREFERENCES = "CuencaVerdeAppPreferences";

    public static final int AUTH = 100;
    public static final int PQRS = 200;
    public static final int ONE_SIGNAL_ID = 300;
    public static final int ROLE = 400;
    private final String AUTH_KEY = "authToken";
    private final String PQRS_KEY = "pqrs";
    private final String ONE_SIGNAL_KEY = "oneSignal";
    private final String ROLE_KEY = "role";

    @Override
    public PreferencesObject getPreferencesObject(int type) {
        PreferencesObject item;
        switch (type) {
            case AUTH:
                item = new PreferencesObject(APP_PREFERENCES, AUTH_KEY, "");
                break;
            case PQRS:
                item = new PreferencesObject(APP_PREFERENCES, PQRS_KEY, "");
                break;
            case ONE_SIGNAL_ID:
                item = new PreferencesObject(APP_PREFERENCES, ONE_SIGNAL_KEY, "");
                break;
            case ROLE:
                item = new PreferencesObject(APP_PREFERENCES, ROLE_KEY, "");
                break;
            default:
                item = null;
        }
        return item;
    }
}
