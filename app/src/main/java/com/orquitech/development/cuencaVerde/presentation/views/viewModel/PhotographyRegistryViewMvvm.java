package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.graphics.Bitmap;

import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface PhotographyRegistryViewMvvm {

    interface View extends AppListView {

        void refreshList();

        void onBackClick();

        void onNewPhoto();

        ViewModel getViewModel();

        void onViewModelUpdated();

        void addNewPhoto(Bitmap image);
    }

    interface ViewModel extends BaseListViewViewModel {

        boolean isHasItemsToDelete();

        void setHasItemsToDelete(boolean hasItemsToDelete);

        void setItem(BaseItem item);

        String getProjectName();

        void getPhotographicRegistries(boolean refresh);

        void getPhotographicRegistries();

        void onBackClick(android.view.View view);

        void onNewPhoto(android.view.View view);

        void deletePhotos(android.view.View view);

        boolean hasProject();

        void addPhotographicRegistryToDeleteList(PhotographicRegistryPhoto photographicRegistryPhoto);

        void removePhotographicRegistryFromDeleteList(PhotographicRegistryPhoto photographicRegistryPhoto);

        void addNewPhoto(Bitmap bitmap);

        void addPendingNewPhoto();
    }
}
