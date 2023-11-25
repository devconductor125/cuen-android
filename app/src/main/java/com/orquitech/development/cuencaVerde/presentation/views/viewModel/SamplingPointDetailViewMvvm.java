package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.os.Bundle;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface SamplingPointDetailViewMvvm {

    interface View extends AppListView {

        void refreshSiembraDetails();

        void onBackClick();

        void openSamplingPointForm(Bundle bundle);
    }

    interface ViewModel extends BaseListViewViewModel {

        void setFeature(Feature feature);

        String getSamplingPointName();

        void getSamplingPoints(boolean refresh);

        void getSamplingPoints();

        void onBackClick(android.view.View view);

        void onNewSamplingPoint(android.view.View view);

        boolean hasTask();
    }
}
