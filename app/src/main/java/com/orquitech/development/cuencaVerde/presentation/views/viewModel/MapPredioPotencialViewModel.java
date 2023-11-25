package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.annotation.SuppressLint;
import android.location.Location;
import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MapPredioPotencialViewModel extends BaseViewModel implements MapPredioPotencialViewMvvm.ViewModel {

    private final MapPredioPotencialViewMvvm.View view;
    private Location lastLocation;
    private String propertyName;

    public MapPredioPotencialViewModel(AppView view) {
        super(view);
        this.view = (MapPredioPotencialViewMvvm.View) view;
    }

    @Override
    public void onReadyForSubscriptions() {
        disposables.add(prediosManager.getPrediosManagementLayerSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(geoJson -> {
                    if (this.view != null && geoJson.features != null) {
                        this.view.onPrediosManagementLayer(geoJson);
                    }
                })
                .subscribe());
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public String getLatitude() {
        return String.valueOf(getLastLocation().getLatitude());
    }

    @Override
    public String getLongitude() {
        return String.valueOf(getLastLocation().getLongitude());
    }

    @Override
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        locationManager.stopGoogleApiClient();
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendPredioPotencial() {
        PredioPotencial predioPotencial = new PredioPotencial();
        predioPotencial.setName(propertyName);
        predioPotencial.setLocation(lastLocation);
        prediosManager.sendPredioPotencial(predioPotencial);
    }

    @Override
    public void setLastLocation(Location location) {
        if (getLastLocation().getLatitude() == 0 && getLastLocation().getLongitude() == 0) {
            prediosManager.getPrediosManagementLayerFromFile(location);
        }
        if (location != null) {
            this.lastLocation = location;
        }
    }

    private Location getLastLocation() {
        if (lastLocation == null) {
            lastLocation = new Location("");
            lastLocation.setLatitude(0);
            lastLocation.setLongitude(0);
        }
        return lastLocation;
    }

    @Override
    public void savePredio(View view) {
        if (!TextUtils.isEmpty(propertyName) && isValidLocation()) {
            this.view.showSavePredioPotencialDialog();
        } else {
            this.view.showMissingInfoMessage(R.string.no_predio_porencial_info, R.color.colorAccent);
        }
    }

    private boolean isValidLocation() {
        return getLastLocation().getLatitude() != 0 && getLastLocation().getLongitude() != 0;
    }

    @Override
    public void onPrediosManagementLayer() {
        prediosManager.clearPrediosManagementLayer();
        prediosManager.getPrediosManagementLayerFromFile(getLastLocation());
    }
}
