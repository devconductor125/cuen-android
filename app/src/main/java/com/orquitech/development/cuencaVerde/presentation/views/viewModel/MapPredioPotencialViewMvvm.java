package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.location.Location;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.MapView;

public interface MapPredioPotencialViewMvvm {

    interface View extends MapView {

        void showSavePredioPotencialDialog();

        void showMissingInfoMessage(int messageResource, int colorSecondary);
    }

    interface ViewModel extends AppViewModel {

        void savePredio(android.view.View view);

        void setLastLocation(Location location);

        void sendPredioPotencial();

        String getPropertyName();

        String getLatitude();

        String getLongitude();

        void setPropertyName(String propertyName);

        void onPrediosManagementLayer();
    }
}
