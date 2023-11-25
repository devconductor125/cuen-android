package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface FeatureDetailsDialogMvvm {

    interface View extends AppView {

        String getString(String content, int resource);

        void dismiss();
    }

    interface ViewModel {

        void setFeature(Feature feature);

        Feature getFeature();

        String getSelectedData();

        String getType();

        String getMaterial();

        String getLength();

        boolean hasLength();

        boolean hasMaterial();
    }
}
