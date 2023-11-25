package com.orquitech.development.cuencaVerde.presentation.views.activities.map;

import android.os.Bundle;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;

public class MapHydrologicalMonitoringProcessActivity extends MapTaskActivity {

    @Override
    public int getViewType() {
        return AppViewsFactory.MAP_HYDROLOGICAL_PROCESS_VIEW;
    }

    @Override
    public void showFeatureDialog(Feature feature) {
        if (feature != null) {
            if (feature.isFromManagementLayer()) {
                showManagementLayerPropertyInfo(feature);
            } else {
                if (feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(RxBus.PAYLOAD, feature);
                    bundle.putParcelable(RxBus.TASK, viewModel.getTask());
                    if (viewModel.getTask().isHydrologicalProcess()) {
                        changeToActivity(AppViewsFactory.HYDROLOGICAL_SAMPLING_POINT_LIST_VIEW, bundle);
                    } else {
                        changeToActivity(AppViewsFactory.EROSIVE_PROCESS_LIST_VIEW, bundle);
                    }
                }
            }
        }
    }
}
