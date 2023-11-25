package com.orquitech.development.cuencaVerde.presentation.views.viewModel.map;

import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.Croquis;
import com.orquitech.development.cuencaVerde.data.model.Material;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;

import java.util.List;

public interface MapTaskViewMvvm {

    interface View extends MapViewMvvm.View {

        void openContractorForm();

        void drawCroquis(List<Croquis> croquis);

        void drawExecutionBaseGeoJson(GeoJson geoJson);

        void drawExecutionEditedGeoJson(GeoJson geoJson);

        int getViewType();

        void hideMeasurementUi();

        void showHydrologicalUi();

        void switchMeasurementButtons(boolean forceOnMeasurementLayout);

        void onMaterialSelected(Action choosenAction, Material material);
    }

    interface ViewModel extends MapViewMvvm.ViewModel {

        void onForm(android.view.View view);

        void onNewTree(android.view.View view);

        void onNewHydrologicalMonitoringPoint(android.view.View view);

        boolean canSendMap();

        void sendMeasurementMap();

        void onGeoJson(GeoJson geoJson);
    }
}
