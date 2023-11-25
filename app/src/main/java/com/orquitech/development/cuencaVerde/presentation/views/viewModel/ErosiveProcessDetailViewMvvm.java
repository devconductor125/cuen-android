package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.os.Bundle;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface ErosiveProcessDetailViewMvvm {

    interface View extends AppListView {

        void refreshSiembraDetails();

        void onBackClick();

        void openErosiveProcessForm(Bundle bundle);
    }

    interface ViewModel extends BaseListViewViewModel {

        void setFeature(Feature feature);

        String getSamplingPointName();

        void getErosiveProcessForms(boolean refresh);

        void getErosiveProcessForms();

        void onBackClick(android.view.View view);

        void onNewErosiveProcessForm(android.view.View view);

        boolean hasTask();
    }
}
