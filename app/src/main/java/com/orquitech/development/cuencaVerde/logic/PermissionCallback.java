package com.orquitech.development.cuencaVerde.logic;

public interface PermissionCallback {

    void onPermissionGiven();

    void onError(String message);
}
