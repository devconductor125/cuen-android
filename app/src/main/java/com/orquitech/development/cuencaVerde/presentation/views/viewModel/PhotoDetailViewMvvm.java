package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface PhotoDetailViewMvvm {

    interface View extends AppView {

        void onCommentSaved();

        void onViewModelUpdated();
    }

    interface ViewModel extends AppViewModel {

        PhotographicRegistryPhoto getPhotographicRegistry();

        void setPhotographicRegistry(PhotographicRegistryPhoto photographicRegistry);

        void onSavePhotoComment(android.view.View view);
    }
}
