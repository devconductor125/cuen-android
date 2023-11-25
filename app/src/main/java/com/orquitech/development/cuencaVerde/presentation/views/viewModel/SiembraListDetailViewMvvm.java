package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.os.Bundle;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface SiembraListDetailViewMvvm {

    interface View extends AppListView {

        void refreshSiembraDetails();

        void onBackClick();

        void openSiembraDetailForm(Bundle bundle);
    }

    interface ViewModel extends BaseListViewViewModel {

        void setFeature(Feature feature);

        String getContractSiembraName();

        void getSiembraDetails(boolean refresh);

        void getSiembraDetails();

        void onBackClick(android.view.View view);

        void onNewSiembraDetail(android.view.View view);

        boolean hasContractSiembra();
    }
}
