package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class CustomDialogViewModel extends BaseViewModel implements CustomDialogMvvm.ViewModel {

    private final CustomDialogMvvm.View dialog;

    public CustomDialogViewModel(CustomDialogMvvm.View appView) {
        super(appView);
        this.dialog = appView;
    }

    @Override
    public void onButtonOne(View view) {
        dialog.onButtonOne();
    }

    @Override
    public void onButtonTwo(View view) {
        dialog.onButtonTwo();
    }
}
