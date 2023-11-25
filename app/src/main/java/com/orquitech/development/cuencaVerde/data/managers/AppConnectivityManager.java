package com.orquitech.development.cuencaVerde.data.managers;

public class AppConnectivityManager implements ConnectivityStatusManager {

    private boolean isConnected;

    public AppConnectivityManager() {

    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
