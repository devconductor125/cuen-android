package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class AddPhotoItemViewModel implements AddPhotoItemViewMvvm.ViewModel {

    private final AddPhotoItemViewMvvm.View view;

    public AddPhotoItemViewModel(AppView view) {
        this.view = (AddPhotoItemViewMvvm.View) view;
    }

    @Override
    public void onClick(View view) {
        this.view.onClick();
    }
}
