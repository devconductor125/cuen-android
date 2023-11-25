package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.graphics.Bitmap;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface MonitorAndMaintenancePhotoItemViewMvvm {

    interface View extends AppView {

        void onClick();
    }

    interface ViewModel extends AppListItemViewModel {

        void onClick(android.view.View view);

        String getImageName();

        void setImageName(String bitmap);

        Bitmap getImage();

        void setImage(Bitmap bitmap);
    }
}
