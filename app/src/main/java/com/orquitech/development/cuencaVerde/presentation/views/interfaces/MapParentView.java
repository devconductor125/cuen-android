package com.orquitech.development.cuencaVerde.presentation.views.interfaces;

import android.content.Context;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;

public interface MapParentView {

    void showToast(String message);

    void showFeatureDialog(Feature feature);

    Context getContext();

    int getDimension(int dimensionResource);

    boolean canAnimateMapCamera();

    boolean canMoveMapCamera();

    void onMarkerClick(Object tag);
}
