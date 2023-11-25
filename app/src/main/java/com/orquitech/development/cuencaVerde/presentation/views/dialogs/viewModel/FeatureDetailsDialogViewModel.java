package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

import java.util.Locale;

public class FeatureDetailsDialogViewModel extends BaseViewModel implements FeatureDetailsDialogMvvm.ViewModel {

    protected final FeatureDetailsDialogMvvm.View view;
    protected Feature feature;

    public FeatureDetailsDialogViewModel(AppView view) {
        super(view);
        this.view = (FeatureDetailsDialogMvvm.View) view;
    }

    @Override
    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    @Override
    public Feature getFeature() {
        return feature;
    }

    @Override
    public String getSelectedData() {
        String selectedData = "";
        if (feature.getProperties().getFeatureType() != null) {
            switch (feature.getProperties().getFeatureType()) {
                case GeoJson.POLYGON:
                case GeoJson.MULTI_POLYGON:
                    selectedData = "Área";
                    break;
                case GeoJson.MULTI_LINE_STRING:
                    selectedData = "Recorrido";
                    break;
                case GeoJson.POINT:
                    selectedData = "Punto";
                    break;
            }
        }
        return this.view.getString(selectedData, R.string.selected_data);
    }

    @Override
    public String getType() {
        return this.view.getString(feature.getProperties().getAccionesTitle(), R.string.data_type);
    }

    @Override
    public String getMaterial() {
        return feature.getProperties().getMaterialTitle();
    }

    @Override
    public String getLength() {
        String unit = "";
        String measurement = "";
        switch (feature.getProperties().getFeatureType()) {
            case GeoJson.POLYGON:
                unit = "m2";
                measurement = String.format(Locale.US, "%.2f", feature.getProperties().getShapeArea());
                break;
            case GeoJson.MULTI_LINE_STRING:
                unit = "m";
                measurement = String.format(Locale.US, "%.2f", feature.getProperties().getShapeLength());
                break;
        }

        return measurement + " " + unit;
    }

    @Override
    public boolean hasLength() {
        return !feature.getProperties().getFeatureType().equals(GeoJson.POINT);
    }

    @Override
    public boolean hasMaterial() {
        return !TextUtils.isEmpty(feature.getProperties().getMaterialId());
    }
}
