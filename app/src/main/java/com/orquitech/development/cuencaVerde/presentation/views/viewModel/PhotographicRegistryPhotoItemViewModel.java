package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.net.Uri;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class PhotographicRegistryPhotoItemViewModel implements PhotographicRegistryPhotoItemViewMvvm.ViewModel {

    private final PhotographicRegistryPhotoItemViewMvvm.View view;
    private final PhotographicRegistryPhoto photographicRegistryPhoto;
    private String imageName;
    private Uri imageUri;
    private boolean deleteActive;

    public PhotographicRegistryPhotoItemViewModel(AppView view, Item item) {
        this.view = (PhotographicRegistryPhotoItemViewMvvm.View) view;
        this.photographicRegistryPhoto = ((PhotographicRegistryPhoto) item);

        this.imageUri = photographicRegistryPhoto.getUri();
        this.imageName = photographicRegistryPhoto.getPhotoName();
    }

    @Override
    public void onClick(View view) {
        if (!deleteActive) {
            this.view.onClick();
        }
    }

    @Override
    public boolean switchDeleteStatus(View view) {
        deleteActive = !deleteActive;
        if (deleteActive) {
            this.view.addPhotographicRegistryToDeleteList(photographicRegistryPhoto);
        } else {
            this.view.removePhotographicRegistryFromDeleteList(photographicRegistryPhoto);
        }
        this.view.onViewModelUpdated();
        return false;
    }

    @Override
    public void switchDeleteStatusFromClick(View view) {
        switchDeleteStatus(view);
    }

    @Override
    public Uri getImageUri() {
        return imageUri;
    }

    @Override
    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public boolean isDeleteActive() {
        return deleteActive;
    }

    @Override
    public PhotographicRegistryPhoto getPhotographicRegistryPhoto() {
        return photographicRegistryPhoto;
    }

    @Override
    public void setDeleteActive(boolean deleteActive) {
        this.deleteActive = deleteActive;
        this.view.onViewModelUpdated();
    }
}
