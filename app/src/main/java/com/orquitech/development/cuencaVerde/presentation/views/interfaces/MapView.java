package com.orquitech.development.cuencaVerde.presentation.views.interfaces;

import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;

public interface MapView extends AppView {

    void getLocation();

    void onPrediosManagementLayer(GeoJson geoJson);
}
