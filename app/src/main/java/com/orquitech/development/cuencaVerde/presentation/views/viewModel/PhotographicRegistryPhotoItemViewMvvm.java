package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.net.Uri;

import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface PhotographicRegistryPhotoItemViewMvvm {

    interface View extends AppView {

        void onClick();

        void onViewModelUpdated();

        void addPhotographicRegistryToDeleteList(PhotographicRegistryPhoto photographicRegistryPhoto);

        void removePhotographicRegistryFromDeleteList(PhotographicRegistryPhoto photographicRegistryPhoto);
    }

    interface ViewModel extends AppListItemViewModel {

        void onClick(android.view.View view);

        boolean switchDeleteStatus(android.view.View view);

        void switchDeleteStatusFromClick(android.view.View view);

        Uri getImageUri();

        void setImageUri(Uri uri);

        boolean isDeleteActive();

        PhotographicRegistryPhoto getPhotographicRegistryPhoto();

        void setDeleteActive(boolean deleteActive);
    }
}
