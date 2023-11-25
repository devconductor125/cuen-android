package com.orquitech.development.cuencaVerde.presentation.views.activities.map;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.orquitech.development.cuencaVerde.databinding.ActivityPredioPotencialMapBinding;
import com.orquitech.development.cuencaVerde.presentation.views.MapViewHelper;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseLocationActivity;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.DialogListenerAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.MapParentView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.MapPredioPotencialViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapPredioPotencialActivity extends BaseLocationActivity implements MapPredioPotencialViewMvvm.View, MapParentView, ToolBarListener {

    private ActivityPredioPotencialMapBinding binding;
    private MapPredioPotencialViewMvvm.ViewModel viewModel;
    private MapViewHelper mapViewHelper;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        mapViewHelper = new MapViewHelper(this);

        if (savedInstanceState != null) {
            this.savedInstanceState = savedInstanceState;
            setUpView();
        }
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        setUpView();
    }

    private void setUpView() {
        if (binding == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_predio_potencial_map);

            updateValuesFromBundle(savedInstanceState);

            viewModel = (MapPredioPotencialViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.MAP_PREDIO_POTENCIAL_VIEW_MODEL);
            binding.setViewModel(viewModel);
            viewModel.onReadyForSubscriptions();

            binding.toolBar.setListener(this);
            binding.toolBar.onLoadingData();

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.setRetainInstance(true);
            mapFragment.getMapAsync(this::setUpMap);
        }
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.keySet().contains(MapViewHelper.REQUESTING_LOCATION_UPDATES)) {
                locationManager.setRequestingLocationUpdates(savedInstanceState.getBoolean(MapViewHelper.REQUESTING_LOCATION_UPDATES));
            }
        }
    }

    private void setUpMap(GoogleMap map) {
        mapViewHelper.setUpMap(map);

        LatLng lastKnownLocation = locationManager.getLastKnownLocation();
        if (lastKnownLocation.latitude != 0 && lastKnownLocation.longitude != 0) {
            mapViewHelper.moveCameraToLocation(lastKnownLocation);
            showMapView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapViewHelper.onResume();
        if (viewModel != null) {
            viewModel.onReadyForSubscriptions();
        }
    }

    @Override
    protected void onPause() {
        mapViewHelper.onPause();
        dialogManager.dismissDialog();
        if (viewModel != null) {
            viewModel.clearSubscriptions();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapViewHelper.onDestroy();
        super.onDestroy();
    }

    protected void onLocationResult(Location location) {
        if (viewModel != null && location != null) {
            viewModel.setLastLocation(location);
            binding.setViewModel(viewModel);
            mapViewHelper.onNewLocation(LocationUtils.locationToLatLng(location));
        }
    }

    @Override
    public void showSavePredioPotencialDialog() {
        dialogManager.showDialog(this, DialogsFactory.SAVE_PREDIO_POTENCIAL, new DialogListenerAdapter() {
            @Override
            public void onButtonTwo(Bundle bundle) {
                viewModel.sendPredioPotencial();
                onPredioPotencialSent();
            }
        });
    }

    @Override
    public void showMissingInfoMessage(int messageResource, int colorSecondary) {
        this.showMessage(getString(messageResource), colorSecondary, R.id.coordinator);
    }

    private void onPredioPotencialSent() {
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onToolbarLeftIconClick() {
        finish();
    }

    @Override
    public void onToolbarMidIconClick() {

    }

    @Override
    public void onToolbarRightEndIconClick() {
        binding.toolBar.onLoadingData();
        mapViewHelper.clearManagementLayer();
        viewModel.onPrediosManagementLayer();
    }

    @Override
    public boolean canAnimateMapCamera() {
        return true;
    }

    @Override
    public boolean canMoveMapCamera() {
        return true;
    }

    @Override
    public void onMarkerClick(Object tag) {
    }

    @Override
    public void showFeatureDialog(Feature feature) {
        showManagementLayerPropertyInfo(feature);
    }

    @Override
    public void onPrediosManagementLayer(GeoJson prediosManagementLayer) {
        binding.toolBar.onFinishedLoadingData();
        mapViewHelper.addPrediosManagementLayer(prediosManagementLayer, serializationManager);
    }

    protected void showMapView() {
        if (binding.mapContainer.getVisibility() == View.INVISIBLE) {
            binding.mapContainer.setVisibility(View.VISIBLE);
        }
    }
}
