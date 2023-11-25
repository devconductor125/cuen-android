package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AppViewModel;

public interface AppFormViewModel extends AppViewModel {

    void saveParcelable(Parcelable parcelable);

    void onBarButtonClick(int position);
}
