package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class PhotoDetailViewModel extends BaseViewModel implements PhotoDetailViewMvvm.ViewModel {

    private PhotoDetailViewMvvm.View view;
    private PhotographicRegistryPhoto photographicRegistryPhoto;

    public PhotoDetailViewModel(AppView view) {
        super(view);
        this.view = (PhotoDetailViewMvvm.View) view;
    }

    @Override
    public void onReadyForSubscriptions() {
        disposables.add(projectsManager.getPhotographicRegistrySubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(photographicRegistryPhoto -> {
                    if (!TextUtils.isEmpty(photographicRegistryPhoto.getId())) {
                        this.photographicRegistryPhoto = photographicRegistryPhoto;
                        this.view.onViewModelUpdated();
                    }
                })
                .subscribe());
    }

    @Override
    public PhotographicRegistryPhoto getPhotographicRegistry() {
        return photographicRegistryPhoto;
    }

    @Override
    public void setPhotographicRegistry(PhotographicRegistryPhoto photographicRegistry) {
        this.photographicRegistryPhoto = (photographicRegistry);
    }

    @Override
    public void onSavePhotoComment(View view) {
        projectsManager.updatePhotographicRegistry(photographicRegistryPhoto);
        this.view.onCommentSaved();
    }
}
